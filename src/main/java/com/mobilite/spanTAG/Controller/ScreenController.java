package com.mobilite.spanTAG.Controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobilite.spanTAG.DTO.ScreenDTO;
import com.mobilite.spanTAG.Entity.Screen;
import com.mobilite.spanTAG.Service.ScreenService;
import com.mobilite.spanTAG.Util.ResponseInfo;


@RestController
@RequestMapping("/screens")
public class ScreenController {

    @Autowired
    private ScreenService screenService;
	ModelMapper mapper = new ModelMapper();
	ResponseEntity<Object> response = null;
    
    @PostMapping
    public ResponseInfo createScreens(@RequestBody List<Screen> screens) {
        ResponseInfo response = new ResponseInfo();

        try {
            // Save the screens using the service
            screenService.saveScreens(screens);

            // Return success response
            response.setResponseType(ResponseInfo.ResponseType.SUCCESS);
            response.setResponseBody("saved successfully");
        } catch (Exception e) {
            // Handle errors and return error response
            response.setResponseType(ResponseInfo.ResponseType.ERROR);
            response.setResponseBody("Failed to save screens: " + e.getMessage());
        }

        return response;
    }

    @GetMapping
    public List<ScreenDTO> getAllScreens() {
        return screenService.getAllScreens();
    }

    @GetMapping("/{id}")
    public ScreenDTO getScreenById(@PathVariable Long id) {
        return screenService.getScreenById(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseInfo> updateScreen(@PathVariable Long id, @RequestBody ScreenDTO screenDTO) {
        try {
            screenService.updateScreen(id, screenDTO);

            ResponseInfo responseInfo = new ResponseInfo(
                ResponseInfo.ResponseType.SUCCESS,
                "Screen updated successfully"
            );

            return ResponseEntity.ok(responseInfo);
        } catch (Exception e) {
            ResponseInfo responseInfo = new ResponseInfo(
                ResponseInfo.ResponseType.ERROR,
                "Failed to update screen: " + e.getMessage()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseInfo);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseInfo> deleteScreen(@PathVariable Long id) {
        try {
            screenService.deleteScreen(id);

            ResponseInfo responseInfo = new ResponseInfo(
                ResponseInfo.ResponseType.SUCCESS,
                "Screen deleted successfully"
            );

            return ResponseEntity.ok(responseInfo);
        } catch (RuntimeException e) {
            ResponseInfo responseInfo = new ResponseInfo(
                ResponseInfo.ResponseType.ERROR,
                "Failed to delete screen: " + e.getMessage()
            );

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseInfo);
        } catch (Exception e) {
            ResponseInfo responseInfo = new ResponseInfo(
                ResponseInfo.ResponseType.ERROR,
                "An unexpected error occurred: " + e.getMessage()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseInfo);
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateScreen(@PathVariable Long id, @RequestBody ScreenDTO screenDTO) {
//        try {
//            screenService.updateScreen(id, screenDTO);
//            return ResponseEntity.ok("Screen updated successfully");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update screen: " + e.getMessage());
//        }
//    }
    
}
