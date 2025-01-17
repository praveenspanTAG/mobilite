package com.mobilite.spanTAG.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilite.spanTAG.DTO.AnnotationDTO;
import com.mobilite.spanTAG.DTO.ScreenDTO;
import com.mobilite.spanTAG.Entity.Annotation;
import com.mobilite.spanTAG.Entity.Screen;
import com.mobilite.spanTAG.Repository.ScreenRepository;


@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;
    
//    @Autowired
//    private AnnotationRepository annotationRepository;
    
    public void saveScreens(List<Screen> screens) {
        for (Screen screen : screens) {
            // Ensure annotations are linked to the screen
            if (screen.getAnnotations() != null) {
                screen.getAnnotations().forEach(annotation -> annotation.setScreen(screen));
            }

            // Save the screen along with annotations
            screenRepository.save(screen);
        }
    }
    
    public List<ScreenDTO> getAllScreens() {
        List<Screen> screens = screenRepository.findAll();
        return screens.stream().map(this::convertToDTO).toList();
    }

    public ScreenDTO getScreenById(Long id) {
        Screen screen = screenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Screen not found with ID: " + id));
        return convertToDTO(screen);
    }

    private ScreenDTO convertToDTO(Screen screen) {
        ScreenDTO dto = new ScreenDTO();
        dto.setId(screen.getId());
        dto.setImage(screen.getImage());
        dto.setAnnotations(
            screen.getAnnotations().stream().map(this::convertAnnotationToDTO).toList()
        );
        return dto;
    }

    private AnnotationDTO convertAnnotationToDTO(Annotation annotation) {
        AnnotationDTO dto = new AnnotationDTO();
        dto.setX(annotation.getX());
        dto.setY(annotation.getY());
        dto.setContent(annotation.getContent());
        return dto;
    }
    
    public void updateScreen(Long id, ScreenDTO screenDTO) {
        Screen screen = screenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Screen not found for ID: " + id));

        // Clear existing annotations and update with new ones
        screen.getAnnotations().clear();

        List<Annotation> updatedAnnotations = screenDTO.getAnnotations()
                .stream()
                .map(annotationDTO -> {
                    Annotation annotation = new Annotation();
                    annotation.setX(annotationDTO.getX());
                    annotation.setY(annotationDTO.getY());
                    annotation.setContent(annotationDTO.getContent());
                    annotation.setScreen(screen); // Set parent reference
                    return annotation;
                }).collect(Collectors.toList());

        screen.getAnnotations().addAll(updatedAnnotations);
        screen.setImage(screenDTO.getImage());

        screenRepository.save(screen);
    }

    public void deleteScreen(Long id) {
        // Check if the screen exists
        if (!screenRepository.existsById(id)) {
            throw new RuntimeException("Screen not found for ID: " + id);
        }

        // Delete the screen
        screenRepository.deleteById(id);
    }

    
//    public void updateScreen(Long id, ScreenDTO screenDTO) {
//        // Fetch the existing screen
//        Screen existingScreen = screenRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Screen not found for ID: " + id));
//
//        // Update the image
//        existingScreen.setImage(screenDTO.getImage());
//
//        // Clear existing annotations and replace with new ones
//        existingScreen.getAnnotations().clear();
//
//        List<Annotation> updatedAnnotations = screenDTO.getAnnotations().stream()
//                .map(dto -> {
//                    Annotation annotation = new Annotation();
//                    annotation.setX(dto.getX());
//                    annotation.setY(dto.getY());
//                    annotation.setContent(dto.getContent());
//                    annotation.setScreen(existingScreen); // Set the relationship
//                    return annotation;
//                })
//                .collect(Collectors.toList());
//
//        existingScreen.getAnnotations().addAll(updatedAnnotations);
//
//        // Save the updated screen
//        screenRepository.save(existingScreen);
//    }

}
