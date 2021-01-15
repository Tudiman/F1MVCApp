package uniWork.f1app.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uniWork.f1app.Entities.Track;
import uniWork.f1app.Services.TrackService;

import java.util.List;

@RestController
@AllArgsConstructor
public class TrackController {

    private final TrackService service;

    @GetMapping("/tracks")
    public List<String> all() {
        return service.all();
    }

    @GetMapping("/tracks/{id}")
    public String get(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/tracksRaw")
    public List<Track> raw() { return service.raw(); }

    @PostMapping("/tracks")
    public Track save(@RequestBody Track track) {
        return service.save(track);
    }

    @PutMapping("/tracks/{id}")
    public String update(@RequestBody Track track, @PathVariable String id) {
        return service.update(track, id);
    }

    @DeleteMapping("/tracks/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
