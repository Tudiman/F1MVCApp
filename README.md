# F1MVCApp

## Descriere
Aplicatia modeleaza un database bazat pe entitati reale ale competitiei Formula 1, respectand reguli de integritate ale constrangerilor reale din competitie, de exemplu fiecare echipa are maximum 2 piloti inregistrati simultan. Aplicatia este separata in 3 layer-uri: Controller layer-ul trimite fiecare request in Service layer, care interogheaza, extinde, modifica sau micsoreaza Model layer-ul. Pentru fiecare tabel din database exista clasa Entity, interfata Repository, clasa Service, clasa Controller, clasa ExceptionNotFoundHandler si clasa ExceptionNotFoundAdvice.

## Dependinte
Aplicatia a fost creata cu Spring Initializr si contine urmatoarele dependinte:
* **lombok** pentru autogenerare de functii generale in entitati
* **Spring MVC Boot** pentru generarea intregii structuri MVC a site-ului si pentru serverul de Tomcat
* **MongoDb** pentru persistenta intr-o baza de date

## Model layer
Layer-ul Model inglobeaza 8 entitati in total, 6 entitati principale si 2 rezolvari M:M. Cele 6 entitati principale sunt Driver, Team, Car, Championship, Race, Track, iar rezolvarile M:M sunt DriverContract, ce rezolva interactiunea intre Driver si Team, precum si ChampionshipRegistration, ce rezolva interactiunea intre Championship si Team.

### Entity si Repository
Clasa Entity, descriind fiecare tabel in parte, contine date de baza despre fiecare reprezentare logica a entitatii, de exemplu Driver contine date despre numele pilotului, data nasterii, numarul preferat, plus metode de baza, precum equals, hashCode si toString(particularizat pentru fiecare Entity, pentru un display cat mai sugestiv, dar nu supraincarcat). In plus, fiecare Entity contine un camp Id, unic generat pentru fiecare obiect din baza de date.
Interfata Repository este creata pentru fiecare Entity in parte, extinde interfata MongoRepository si, pe langa functiile CRUD generate automat, contine query-uri particulare, in functie de nevoie, pentru fiecare clasa. De exemplu, repository-ul pentru DriverContract contine antetul functiei `findByDriverIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(String id, Integer endDate, Integer startDate)`, care genereaza un query ce gaseste toate DriverContracts dupa DriverId dat ca parametru, dar care au intervalul generat de startDate si endDate suprapus cu un alt interval dat ca parametru. MongoRepository genereaza query in functie de antetul functiei create in interfata, in functie de cuvintele cheie si denumirile de campuri date in antet. Putem de asemenea sa specificam si campuri din obiecte continute de obiectul principal, de exemplu `driverContract.driver.id`;

## Service layer
In acest layer se intampla toata actiunea din cadrul aplicatiei, acest layer fiind middle-man-ul dintre Controller si Model. Fiecare entitate are un service corespunzator, iar fiecare service inglobeaza un template cu functii CRUD, plus functionalitati specifice fiecarei entitati. Functiile ce apar in fiecare service sunt:
1. `all()`, ce intoarce o lista cu toate entitatile din database in varianta toString(), pentru afisare concisa;
2. `raw()`, ce intoarce o lista cu toate entitatile din database in varianta JSON, pentru detalii tehnice, precum id;
3. `findById(String id)`, ce intoarce un entity dupa id, sau arunca exceptie specifica entitatii in cazul in care nu s-a gasit entitatea;;
4. `save(Entity entity)`, ce incearca sa insereze un nou entity in baza de date, in functie de restrictii, si intoarce string ori de confirmare, ori cu motivul pentru care nu s-a putut insera;
5. `update(Entity entity, String id)`, ce incearca sa modifice un entity in baza de date, in functie de restrictii, dupa id-ul dat, si intoarce string ori de confirmare, ori cu motivul pentru care nu s-a putut modifica, ori arunca exceptie specifica entitatii in cazul in care nu s-a gasit entitatea;
6. `delete(String id)`, ce sterge un entity dupa id, sau arunca exceptie specifica entitatii in cazul in care nu s-a gasit entitatea;

Functii specifice fiecarui service vor fi detaliate in capitolul **Business and Functional Requirements**.

## Controller layer
Controllerele sunt clasele care permit interactiunea cu browser-ul. Fiecare entity are propriul controller, in care sunt mapate, indiferent de controller, urmatoarele rute principale:
1. `GetMapping("/entities)` pentru apelul functiei `all()` din service;
2. `GetMapping("/entitiesRaw")` pentru apelul functiei `raw()` din service;
3. `GetMapping("/entities/{id})` pentru apelul functiei `findById(id)` din service, unde `id` este preluat cu `@PathVariable` din ruta;
4. `PostMapping("/entities)` pentru apelul functiei `save(Entity entity)` din service, unde `entity` este preluat cu `@RequestBody` din Post, in format JSON;
5. `PutMapping("/entities/{id})` pentru apelul functiei `update(entity, id)` din service, unde `entity` este preluat cu `@RequestBody` din Post, in format JSON, iar`id` este preluat cu `@PathVariable` din ruta;
6. `DeleteMapping("/entities/{id})` pentru apelul functiei `delete(id)` din service, unde `id` este preluat cu `@PathVariable` din ruta;
In plus, metodele de `POST` si `UPDATE` din controllerele entitatilor DriverContract si ChampionshipRegistration accepta EntityTransferObject pentru fiecare entitate in parte.
Deoarece aceste entitati contin clase intregi drept campuri, nu se pot adauga simplu in @RequestBody prin JSON, de aceea exista clase speciale, care sunt interpretate in service-urile entitatilor respective si apoi pasate mai departe functiilor de `save` si `update`.

Rute specifice fiecarui controller vor fi detaliate in capitolul **Business and Functional Requirements**.

## Business and Functional Requirements

### Functional Requirements
Este implementat un set de reguli de validare peste nivelul de datatype validation, ce este manageriat automat de Java. Aceste reguli vin pentru a forta utilizatorul sa respecte reguli existente in competitia de Formula 1.

#### Contractele nu se suprapun
Entitatea DriverContract vine sa rezolve relatia M:M intre Driver si Team si sa adauge un nivel logic acestei relatii, logica temporala. Mai exact, un pilot este inscris la o echipa intr-un interval bine definit de timp, in ani. Astfel, apare problema suprapunerii contractelor, atunci cand se incearca adaugarea, prin inserare sau editare, a unui nou contract.
Aceasta problema se rezolva printr-un query aplicat la fiecare inserare sau editare, care verifica suprapuneri intre noile date si contractele pre-existente. Functiile din DriverContractService apeleaza fiecare un query personalizat pentru a determina suprapuneri:
* `save(DriverContract driverContract)` apeleleaza query care gaseste toate contractele dupa `driverContact.driver.id` si se aplica formula (d1.start <= d2.end && d1.end >= d2.start), unde d1 e fiecare contract din database cu DriverId-ul cerut, iar d2 este noul driverContract primit ca parametru.
* `update(DriverContract updatedDriverContract, String id)` apeleaza un query similar, dar care mai presus de asta, nu selecteaza si contractul ce urmeaza sa fie modificat. Daca aplicam acelasi query ca la save, datele noi de contract pot fi considerate invalide din cauza contractului ce dorim a fi modificat, ceea ce este clar incorect.

#### Echipele pot avea maxim doi piloti contractati in orice an
Precum si in viata reala, o echipa nu poate contracta mai mult de doi piloti simultan, si aceasta constrangere se resimte si in aceasta aplicatie. Aplicand query-uri echivalente cu cele de suprapunere pentru save si, respectiv, update, dar in care cautam dupa `driverContract.team.id` in loc de `driverContact.driver.id`, obtinem toate contractele ce se suprapun cu noul contract. Dar, dat fiind faptul ca pot fi doua simultan, nu doar unul, trebuie sa ne asiguram ca nu exista nicio pereche de contracte din cele pre-existente gasite care sa se suprapuna in orice an. Astfel, aplicam functia `checkTeamAvailability(List<DriverContract> list)`, unde aplicam formula de verificare a suprapunerilor de mai sus pe fiecare pereche de contracte in complexitate O(list.size()^2). Indiferent de dimensiunea listei, daca nu se gaseste nicio suprapunere, inseamna ca intotdeauna exista un loc liber la echipa in perioada specificata de noul contract, iar acesta poate fi introdus sau modificat in baza de date.

#### Fiecare echipa se poate inscrie o singura data la un campionat
Inscrierea unei echipe la un campionat se face prin entitatea ChampionshipRegistration, iar anul inscrierii este definit de anul campionatului, ceea ce inseamna ca aceasta entitate contine date doar despre echipa si campionat. Prin urmare, putem gasi printr-un query dupa numele echipei si anul campionatului daca exista deja astfel de inregistrare si sa actionam in functie de acest rezultat.

### Business Requirements
Business Requirements inglobeaza query-uri complexe care nu tin de validare, sau de functionalitatea modelului. Toate aceste query-uri au radacini in repository si tulpina pana in controllere.

#### Clasamentul fiecarui campionat
Pe ruta `"/championships/rankings"` este mapata functia `rankings()` ce intoarce mesaj ed ghidare catre utilizarea acestui query. Adaugand `/{year}` la ruta, obtinem clasamentul pilotilor in campionatul din anul respectiv. Acest query pleaca din RaceRepository, cu simplul query `findByYear(Integer year)` si ajunge in RaceService, unde, bazandu-se pe campul `List<String> rankings` din entitate si campul static `static List<Integer> scoring` din service, genereaza un HashMap unde cheia este pilotul si valoarea este numarul total de puncte stranse adunat din lista generata de query-ul de mai sus, o sorteaza descrescator dupa punctele adunate, o transforma in lista de perechi pilot-puncte, si o returneaza.

#### Campionul fiecarui an
Pe ruta `"/championships/champions"` este mapata functia `champions()` din ChampionshipService, care apeleaza functia `rankings(Integer year)` din RaceService pentru fiecare campionat existent in baza de date si intoarce un text stilizat cu numele celui din varful clasamentului, punctele stranse, si anul campionatului.
Daca mergem un pas mai departe si accesam ruta `"/championships/champions/{year}"`, putem gasi campionul pe anul dat ca parametru.

#### Pilotul cu cele mai multe campionate castigate
In DriverController se regasteste ruta `"/drivers/mostTitles"`, care apeleaza ChampionshipService pentru a genera intr-un HashMap(cheile fiind campionii si valorile numarul de campionate castigate), prin intermediul RaceService, campionul fiecarui campionat existent in baza de date. Pentru fiecare campionat, daca exista campionul in baza de date, se incrementeaza numarul de campionate castigate cu 1, altfel se adauga o cheie noua in HashMap, dupa numele campionului si i se atribuie valoarea 1. La final, Functia cauta campionul cu numar maxim de campionate castigate prin intermediul metodei `max` din clasa Collections si folosind comparatorul `comparingInt` din clasa Comparator. 

#### Istoricul masinilor unei echipe
Controllerul pentru echipe mapeaza trei rute in acest sens, ruta `"/teams/cars"` pentru detalii despre aceasta functie, `"/teams/cars/{name}"` pentru a vedea toate masinile unei echipe dupa nume, si ruta `"/teams/cars/{name}/{year}"` pentru a specifica si anul dorit. Ultimele doua rute pleaca din repository-ul masinilor, apeleaza query-uri pentru `findByTeam` si `findByTeamAndYear`, intorc rezultatul in CarService, care mai departe intoarce rezultatul in TeamController.

#### Sortarea masinilor dupa performanta
In controllerul pentru masini exista rutele `"/cars/sort"`, informativa, si `"/cars/sort/{year}"`, pentru a afisa masinile respectivului an in ordinea performantei, cunoscuta in fiecare entitate Car. Controllerul apeleaza functia `sortByYear(Integer year)` din CarService, care extrage din database toate masinile prin functia `findByYear` si le sorteaza dupa metoda `compareTo` definita in entitatea Car, care implementeaza interfata `Comparable<Car>`. Astfel, functia sort va sorta masinile de la cea mai mare performanta la cea mai mica si le va intoarce in controller.
