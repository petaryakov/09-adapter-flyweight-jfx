package ohm.softa.a09.model;

import ohm.softa.a09.model.empire.TieBomber;
import ohm.softa.a09.model.empire.TieFighter;
import ohm.softa.a09.model.empire.TieInterceptor;
import ohm.softa.a09.model.rebellion.AWing;
import ohm.softa.a09.model.rebellion.XWing;
import ohm.softa.a09.model.rebellion.YWing;
import ohm.softa.a09.resource.FxImageLoaderAdapter;
import ohm.softa.a09.resource.ResourceLoader;
import ohm.softa.a09.util.NameGenerator;
import javafx.scene.image.Image;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Random;


/**
 * Factory to create new fighters
 * Creates random fighters
 *
 * @author Peter Kurfer
 */
public final class FighterFactory {

	private static final int NumberOfKnownFighterTypes = 6;
	private final Random random;
	private final NameGenerator nameGenerator;
	//private final ResourceLoader<Image> imageResourceLoader;
	private final FxImageLoaderAdapter imageLoader = new FxImageLoaderAdapter();
	// cache for storage of images
	private HashMap<String, Image> cache = new HashMap<>();

	public FighterFactory() {
		nameGenerator = new NameGenerator();
		random = new Random();
		//imageResourceLoader = new ResourceLoader<>(Image::new);
	}

	/**
	 * Create a random Fighter
	 *
	 * @implNote the method has an implementation flaw because it always loads the fighters image
	 * @return a new Fighter instance
	 */
	public Fighter createFighter() {
		// was genau macht classloader?
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		String name = nameGenerator.generateName();

		switch (random.nextInt(NumberOfKnownFighterTypes)) {
			case 0:
				return new AWing(name, loadImage(cl,"fighter/awing.jpg"));
			case 1:
				return new XWing(name, loadImage(cl,"fighter/xwing.jpg"));
			case 2:
				return new YWing(name, loadImage(cl,"fighter/ywing.jpg"));
			case 3:
				return new TieBomber(name, loadImage(cl,"fighter/tiebomber.jpg"));
			case 4:
				return new TieFighter(name, loadImage(cl,"fighter/tiefighter.jpg"));
			default:
				return new TieInterceptor(name, loadImage(cl,"fighter/tieinterceptor.jpg"));
		}
		/*switch (random.nextInt(NumberOfKnownFighterTypes)) {
			case 0:
				return new AWing(nameGenerator.generateName(), imageResourceLoader.loadResource(ClassLoader.getSystemClassLoader(), "fighter/awing.jpg"));
			case 1:
				return new XWing(nameGenerator.generateName(), imageResourceLoader.loadResource(ClassLoader.getSystemClassLoader(), "fighter/xwing.jpg"));
			case 2:
				return new YWing(nameGenerator.generateName(), imageResourceLoader.loadResource(ClassLoader.getSystemClassLoader(), "fighter/ywing.jpg"));
			case 3:
				return new TieBomber(nameGenerator.generateName(), imageResourceLoader.loadResource(ClassLoader.getSystemClassLoader(), "fighter/tiebomber.jpg"));
			case 4:
				return new TieFighter(nameGenerator.generateName(), imageResourceLoader.loadResource(ClassLoader.getSystemClassLoader(), "fighter/tiefighter.jpg"));
			default:
				return new TieInterceptor(nameGenerator.generateName(), imageResourceLoader.loadResource(ClassLoader.getSystemClassLoader(), "fighter/tieinterceptor.jpg"));
		}*/
	}

	private Image loadImage(ClassLoader cl, String path){
		if(!cache.containsKey(path)) {
			//cache.put(path, imageResourceLoader.loadResource(cl, path));
			cache.put(path, imageLoader.loadImage(path));
		}

		return cache.get(path);
	}
}
