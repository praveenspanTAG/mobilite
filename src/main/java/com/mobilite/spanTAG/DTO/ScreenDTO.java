package com.mobilite.spanTAG.DTO;

import java.util.List;

public class ScreenDTO {
    private Long id;
    private String image;
    private List<AnnotationDTO> annotations;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<AnnotationDTO> getAnnotations() {
		return annotations;
	}
	public void setAnnotations(List<AnnotationDTO> annotations) {
		this.annotations = annotations;
	}

}
