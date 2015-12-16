package com.data;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.taglibs.standard.lang.jstl.ELException;

/* for non-DB related operation and methods */
public class Misc {

	public static String daysBetween(Date t1, Date t2) throws ELException {
		TimeUnit tuDays = TimeUnit.DAYS;
		/*TimeUnit tuHours = TimeUnit.HOURS;*/
		long timeDiff = t2.getTime() - t1.getTime();
		String daysStr = null;

		long days = tuDays.convert(timeDiff, TimeUnit.MILLISECONDS);
		long hours = TimeUnit.MILLISECONDS.toHours(timeDiff) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(timeDiff));
		//System.out.println(days + ", " + hours	);

		/*if(days == 0 || (days == 0 && hours <= 12)) {
			daysStr = "Today!";
		} else*/ if(days < 0 || hours < 0) {
			/*daysStr = "Completed";*/
			throw new ELException("Completed!");
		} else if(days == 0) {
			daysStr = hours + " Hr(s) left";
		} else {
			/*if(days == 0 && hours > 12) {
				days++;
				hours = 0;
			}*/
			daysStr = days + " day(s) " + hours + " Hr(s) left";
		}


		return daysStr;
	} //daysBetween

	/* file operations on a server */

	public static File[] listFilesInDir(String dir, char sortOrder) throws Exception {
		File[] files = null;
		final char sOrder = sortOrder; // 'd' for descending 'a' for ascending


		try {
			files = new File(dir).listFiles();


			/*for(File file: files) {
				System.out.println(file.getName());
				System.out.println(file.lastModified());
				System.out.println(file.length());
			}*/

			//sorting from present to past
			Arrays.sort(files, 
					new Comparator<File>() {
				public int compare(File f1, File f2) {
					//System.out.println(f2.lastModified() - f1.lastModified());
					if(sOrder == 'd') 
						return (int)(f2.lastModified() - f1.lastModified());
					return (int)(f1.lastModified() - f2.lastModified());
				}
			});
		} catch (Exception e) {
			new Exception("Invalid directory!");
			//e.printStackTrace();
		}

		return files;
	} //listFilesInDir
	
	public static String getValidURL(File file) {
		String url = null;
		
		//spaces need to be replaced with %20 in a URL
		url = file.toString();
		System.out.println(url);
		url = url.replace(" ", "%20");
		url = url.replace("\\", "/");
		System.out.println(url);
		
		return url;
	}
	
	public static String getValidParameterURI(File file) {
		String parameter = null;
		
		parameter = file.getName().replace(" ", "+");
		//System.out.println(parameter);
		
		return parameter;
	}
	
	private static final long MILLIS_IN_A_DAY = 24 * 60 * 60 * 1000L;
	public static boolean isWithin24Hrs(Date current, Date reference) {
		return ((current.getTime() - reference.getTime()) < MILLIS_IN_A_DAY);
	}
} //Misc class
