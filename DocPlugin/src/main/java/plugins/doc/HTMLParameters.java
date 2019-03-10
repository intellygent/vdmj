/*******************************************************************************
 *
 *	Copyright (c) 2019
 *
 *	Author: Paul Chisholm
 *
 *	This file is part of VDMJ.
 *
 *	VDMJ is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	VDMJ is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU General Public License
 *	along with VDMJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 ******************************************************************************/

package plugins.doc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HTMLParameters
{
    private static final String CSS_FILENAME = "cssfilename";
    private static final String CUSTOM_FILENAME = "customfilename";
    private static final String SYMBOL_FILENAME = "symbolfilename";
    private static final String MAX_WIDTH = "maxwidth";
    private static final String STEP = "step";
    private static final String FARG_NO_PAREN = "fargnoparen";
    
    public static String cssFile;
    public static String customFile;
    public static String symbolFile;
    public static int maxWidth;
    public static int step;
    public static boolean fargNoParen;
    
    public static String load(String file)
    {
        String result = null;
        Properties prop = new Properties();
        InputStream is = null;
        try
        {
            is = new FileInputStream(file);
            prop.load(is);
            is.close();
            cssFile = prop.getProperty(CSS_FILENAME);
            if (cssFile == null)
            {
                return String.format("Property '%s' missing", CSS_FILENAME);
            }
            customFile = prop.getProperty(CUSTOM_FILENAME);
            symbolFile = prop.getProperty(SYMBOL_FILENAME);
            if (symbolFile != null)
            {
                try
                {
                    Symbol.load(symbolFile);
                }
                catch (Exception e)
                {
                    return String.format("Failure loading symbol file: %s\n%s", symbolFile, e.toString());
                }
            }
            String maxWidthS = prop.getProperty(MAX_WIDTH);
            if (maxWidthS == null)
            {
                maxWidth = 80;
            }
            else
            {
                try
                {
                    maxWidth = Integer.parseInt(maxWidthS);
                }
                catch (Exception e)
                {
                    return String.format("Invalid maxWidth parameter: '%s'", maxWidthS);
                }
            }
            String stepS = prop.getProperty(STEP);
            if (stepS == null)
            {
                step = 2;
            }
            else
            {
                try
                {
                    maxWidth = Integer.parseInt(stepS);
                }
                catch (Exception e)
                {
                    return String.format("Invalid step parameter: '%s'", stepS);
                }
            }
            String noParen = prop.getProperty(FARG_NO_PAREN);
            fargNoParen = noParen != null && !noParen.isEmpty();
        }
        catch (IOException e)
        {
            try{is.close();} catch (Exception f) {}
            result = "Failure reading configuration file";
        }
        return result;
    }
}
