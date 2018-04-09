/**
 * 
 */
package com.nearshoretechnology.focalpoint.common.util;

import java.net.URI;

/**
 * @author earteaga
 *
 */
public class StringUtils {

	public static String toCamelCase(final String init) {

		if (init == null)
			return null;

		final StringBuilder ret = new StringBuilder(init.length());

		for (final String word : init.split("( |_)")) {
			if (!word.isEmpty()) {
				ret.append(word.substring(0, 1).toUpperCase());
				ret.append(word.substring(1).toLowerCase());
			}
			if (!(ret.length() == init.length()))
				ret.append(" ");
		}

		return ret.toString();
	}

	public static String extractBasePathFromString(String candidate) {

		URI uri = URI.create(candidate);
		final String path = uri.getPath();

		return candidate.replace(path, "");
	}

	public static boolean isBlank(String value) {

		return org.apache.commons.lang3.StringUtils.isBlank(value);
	}

}
