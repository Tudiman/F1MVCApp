package uniWork.f1app.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uniWork.f1app.Entities.Track;
import uniWork.f1app.Exceptions.TrackNotFoundException;
import uniWork.f1app.Repositories.TrackRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrackService {

    private final TrackRepository repository;

    public List<String> all() {
        return repository.findAll().stream().map(Track::toString).collect(Collectors.toList());
    }

    public String findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new TrackNotFoundException(id))
                .toString();
    }

    public List<Track> raw() {
        return repository.findAll();
    }

    public Track save(Track track) {
        return repository.save(track);
    }

    public String update(Track updatedTrack, String id) {
        return repository.findById(id)
                .map(track -> {
                    track.setName(updatedTrack.getName());
                    track.setLength(updatedTrack.getLength());
                    track.setCountry(updatedTrack.getCountry());
                    track.setInauguralYear(updatedTrack.getInauguralYear());
                    return repository.save(track);
                }).orElseThrow(() -> new TrackNotFoundException(id))
                .toString();
    }

    public void delete(String id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
        else throw(new TrackNotFoundException(id));
    }
}
