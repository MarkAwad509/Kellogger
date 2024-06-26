package edu.csusm.kellogger.Services;

import edu.csusm.kellogger.Model.Space;
import edu.csusm.kellogger.Repository.ISpaceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SpaceService implements ISpaceService{

    @Autowired private ISpaceRepository spaceRepository;

    @Override
    public List<Space> getAllSpaces() {
        return spaceRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public Optional<Space> getSpace(int id){
        return spaceRepository.findById(id);
    }

    @Override
    public Space createSpace(Space space){
        return spaceRepository.save(space);
    }

    @Override
    public Space deleteSpace(int id) {
        var spaceOptional = spaceRepository.findById(id);
        spaceRepository.deleteById(id);
        return spaceOptional.orElse(null);
    }

    @Override
    public Space addPerson(int id) {
        Optional<Space> spaceOptional = getSpace(id);
        if (spaceOptional.isPresent()){
            Space s = spaceOptional.get();
            var count = s.getPeopleCount();
            s.setPeopleCount(++count);
            var usage = s.getUsage();
            s.setUsage(usage);
            return spaceRepository.save(s);
        }
        return null;
    }

    @Override
    public Space removePerson(int id) {
        Optional<Space> spaceOptional = getSpace(id);
        if (spaceOptional.isPresent()){
            Space s = spaceOptional.get();
            var count = s.getPeopleCount();
            s.setPeopleCount(--count);
            s.setUsage(s.getUsage());
            return spaceRepository.save(s);
        }
        return null;
    }

    @Override
    public int getCount(Space space) {
        Optional<Space> spaceOptional = getSpace(space.getId());
        if (spaceOptional.isPresent()){
            Space s = spaceOptional.get();
            return s.getPeopleCount();
        }
        return 0;
    }
}
