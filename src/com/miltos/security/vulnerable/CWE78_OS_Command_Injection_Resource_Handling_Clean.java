/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE78_OS_Command_Injection__File_01.java
Label Definition File: CWE78_OS_Command_Injection.label.xml
Template File: sources-sink-01.tmpl.java
*/
/*
* Modified by: Miltiadis Siavvas
*/
package com.miltos.security.clean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.stream.Stream;

public class CWE78_OS_Command_Injection_Resource_Handling_Clean {
	
	final static String DATA = new File("C:\\Users\\siavvasm.ITI-THERMI.000\\Desktop\\input_data.txt").getAbsolutePath();

	public static void bad(final String param) throws Exception {
		
		//TODO: Remove this print 
		System.out.println("*** Start of potentially vulnerable method ***");
		
		// 1. Define the data of the command
		String data;
		data = param;
		
		// 2. Define the command
        String osCommand;
        if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)
        {
            /* running on Windows */
            osCommand = "c:\\WINDOWS\\SYSTEM32\\cmd.exe /c dir ";
        }
        else
        {
            /* running on non-Windows */
            osCommand = "/bin/ls ";
        }
        
        // 3. Execute the command
        /* POTENTIAL FLAW: command injection */
        final Process process = Runtime.getRuntime().exec(osCommand + data);
        process.waitFor();
        osCommand = null;
        data = null;
        
		//TODO: Remove this print 
		System.out.println("*** End of potentially vulnerable method ***");
		
	}
	
    public static void good(final String param) throws Exception {
    	
    	// 1. Read the parameter
        String data = param;

        /* FIX: Use a hardcoded string */
        /* Replace the parameter with a benign String */
        data = null;

        String osCommand;
        if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
            /* running on Windows */
            osCommand = "c:\\WINDOWS\\SYSTEM32\\cmd.exe /c dir ";
        } else {
            /* running on non-Windows */
            osCommand = "/bin/ls ";
        }

        /* POTENTIAL FLAW: command injection */
        final Process process = Runtime.getRuntime().exec(osCommand + data);
        process.waitFor();

    }
    
	public static void main(final String[] args) throws Exception {
		
		//TODO: Remove this print
		System.out.println("*** Executing an application that is vulnerable to OS Command Injection ...");
		
		// 1. Open the desired file
		final FileReader fr = new FileReader(DATA);
		final BufferedReader br = new BufferedReader(fr);
		
		// 2. Read the user defined parameters from the desired file
		Stream<String> parameters = br.lines();
		Iterator<String> parameterIt = parameters.iterator();
		
		String parameter = "";

		
		// 3. Execute the command for each parameter
		while(parameterIt.hasNext()) {
			
			// Read the parameter
			parameter = parameterIt.next();
			
			// Execute the command with this parameter
			if(!parameter.equals(null)){
				bad(parameter);
			}

			//TODO: Remove this print
			System.out.println(parameter);
		}
		
		try {
			if (br != null) {
				br.close();
			}
		} catch (IOException e) {
			System.out.println("Error closing the BufferedReader!");
			System.out.println(e.getMessage());
		}
		
		try {
			if (fr != null) {
				fr.close();
			}
		} catch (IOException e) {
			System.out.println("Error closing the FileReader!");
			System.out.println(e.getMessage());
		}
		
	}


}
