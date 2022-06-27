package com.douzone.jblog.exception;

public class FileUploadException extends Exception {
	private static final long serialVersionUID = 1L;

	public FileUploadException() {
		super("GalleryServiceException Occurs");
	}

	public FileUploadException(String message) {
		super(message);
	}
}
