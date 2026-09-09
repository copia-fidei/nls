package com.epau.utilities.nls;

import org.jetbrains.annotations.NonNls;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static java.util.ResourceBundle.getBundle;
import static java.util.logging.Level.WARNING;
import static java.util.logging.Logger.getLogger;

@NonNls
public class Nls {

	private static final Logger LOG = getLogger(Nls.class.getName());

	private final ResourceBundle bundle;

	public Nls(Class<?> cls) {
		this.bundle = getBundle(cls.getPackageName() + ".messages", cls.getModule());

//		LOG.info(() -> String.format("%nClass: %s%nModule: %s%nLocale: %s%nBundle: %s%nBundle locale: %s%n", //NON-NLS
//				cls.getName(),
//				cls.getModule(),
//				Locale.getDefault(),
//				bundle,
//				bundle.getLocale()
//		));
	}

	public Nls(Object cls) {
		this(cls.getClass());
	}

	public String get(String key) {
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			LOG.log(WARNING, "Missing resource: " + key, e);
			return key;
		}
	}

	public String get(String key, Object... args) {
		return MessageFormat.format(get(key), args);
	}
}

