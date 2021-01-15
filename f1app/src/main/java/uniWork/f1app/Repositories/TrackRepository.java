package uniWork.f1app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import uniWork.f1app.Entities.Track;

public interface TrackRepository extends MongoRepository<Track, String> {
}
