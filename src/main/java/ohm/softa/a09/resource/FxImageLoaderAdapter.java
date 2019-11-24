package ohm.softa.a09.resource;

import javafx.scene.image.Image;

public class FxImageLoaderAdapter {
	private ResourceLoader<Image> resourceLoader;

	public FxImageLoaderAdapter(){
		resourceLoader = new ResourceLoader<>(Image::new); // Syntax is Image::new because ResourceLoader expects a function as argument and we give the new() from Image
	}

	public Image loadImage(String path){
		return resourceLoader.loadResource(ClassLoader.getSystemClassLoader(), path);
	}
}
