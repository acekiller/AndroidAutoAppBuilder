package pl.radomski.autobuilder.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Stream Utils.
 */
public class StreamUtils {

	public static long copy(InputStream input, OutputStream output) throws IOException {
		try {
			byte[] buffer = new byte[1024 * 4];
			long count = 0;
			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
				count += n;
			}
			return count;
		} finally {
			close(input, output);
		}
	}

	/**
	 * Closes Closable objects.
	 * 
	 * @param closeable
	 *            the closeable objects
	 */
	public static void close(final Closeable... closeable) {
		for (Closeable close : closeable) {
			try {
				if (close != null) {
					close.close();
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Reads file to string.
	 * 
	 * @param file
	 *            the file
	 * @return the string
	 * @throws IOException
	 */
	public static String fileToString(File file) throws IOException {
		return streamToString(new FileInputStream(file));
	}

	/**
	 * Parses stream to string.
	 * 
	 * @param inputStream
	 *            the inputStream
	 * @return the string
	 * @throws IOException
	 */
	public static String streamToString(InputStream inputStream) throws IOException {
		final String lineSeparator = System.getProperty("line.separator");
		StringBuilder builder = new StringBuilder();
		BufferedReader bufIn = null;
		bufIn = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		while ((line = bufIn.readLine()) != null) {
			builder.append(line + lineSeparator);
		}
		close(bufIn);
		return builder.toString();
	}

	/**
	 * Copies file to selected dir.
	 * 
	 * @param fileSource
	 *            the file source
	 * @param outputDir
	 *            the dir
	 * @throws IOException
	 */
	public static void copyFile(File fileSource, File outputDir) throws IOException {

		FileInputStream in = null;
		FileOutputStream out = null;
		in = new FileInputStream(fileSource);
		out = new FileOutputStream(outputDir.getAbsolutePath() + "/" + fileSource.getName());

		int c;

		byte[] bs = new byte[1024];

		while ((c = in.read(bs)) > 0) {
			out.write(bs, 0, c);
		}
		close(in, out);
	}

	/**
	 * Deletes directory.
	 * 
	 * @param directory
	 *            the directory
	 * @return true, if successful
	 */
	public static boolean deleteDir(File directory) {
		if (directory.isDirectory()) {
			String[] children = directory.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(directory, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// The directory is now empty so delete it
		return directory.delete();
	}

	/**
	 * Writes input stream to file.
	 * 
	 * @param outputFile
	 *            the output file
	 * @param inputStream
	 *            the input stream
	 * @throws IOException
	 */
	public static void inputStreamToFile(File outputFile, InputStream inputStream) throws IOException {
		OutputStream out = null;
		if (!outputFile.exists()) {
			if (!outputFile.createNewFile()) {
				throw new IOException("File" + outputFile + " cannot be created");
			}
		}

		out = new FileOutputStream(outputFile);

		byte buf[] = new byte[1024];
		int len;
		while ((len = inputStream.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		out.flush();
		close(inputStream, out);
	}
}
