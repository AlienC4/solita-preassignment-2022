package backend.controller;

import backend.domain.Farm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.repository.FarmRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/farms")
public class FarmsController {

    private final FarmRepository farmRepository;

    public FarmsController(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @GetMapping
    public List<Farm> getFarms() {
        List<Farm> farms = farmRepository.findAll();
        return farms;
    }

    @GetMapping("/{id}")
    public Farm getFarm(@PathVariable Long id) {
        return farmRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity createFarm(@RequestBody Farm farm) throws URISyntaxException {
        Farm savedFarm = farmRepository.save(farm);
        return ResponseEntity.created(new URI("/farms/" + savedFarm.getId())).body(savedFarm);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFarm(@PathVariable Long id, @RequestBody Farm farm) {
        Farm currentFarm = farmRepository.findById(id).orElseThrow(RuntimeException::new);
        currentFarm.setName(farm.getName());
        currentFarm = farmRepository.save(farm);
        System.out.println(currentFarm);

        return ResponseEntity.ok(currentFarm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFarm(@PathVariable Long id) {
        farmRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}