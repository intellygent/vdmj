/** *****************************************************************************
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
 *****************************************************************************
 */
package plugins.doc;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/*
  This class captures a mapping between keywords and there output presentation.

  By default the ASCII syntax is employed. Any keyword included in the symbol table
  is presented as per the mapping.
*/
public class Symbol
{
    private static Map<String, Characteristic> symbols = new HashMap();

    // Read symbol definitions from a file.
    public static void load(String file) throws Exception
    {
        LineNumberReader in = null;
        in = new LineNumberReader(new FileReader(file));
        String line;
        do
        {
            line = in.readLine();
            if (line != null)
            {
                String[] tokens = line.split("\\h+");
                String key = tokens[0];
                String entity = tokens[1];
                int width = 1;
                if (tokens.length > 2)
                {
                    width = Integer.parseInt(tokens[2]);
                }
                symbols.put(key, new Characteristic(entity, width));
            }
        }
        while (line != null);
        in.close();
    }

    // The output representation of a symbol.
    public static String get(String sym)
    {
        Characteristic c = symbols.get(sym);
        if (c == null)
        {
            return sym;
        }
        else
        {
            return c.value;
        }
    }
    
    // The size (number of characters) of a symbol.
    public static int size(String sym)
    {
        Characteristic c = symbols.get(sym);
        if (c == null)
        {
            return sym.length();
        }
        else
        {
            return c.width;
        }
    }
    
    /*
      The characteristics of a symbol.
    */
    private static class Characteristic
    {
        String value;
        int width;
        
        Characteristic(String value, int width)
        {
            this.value = value;
            this.width = width;
        }

        Characteristic(String value)
        {
            this(value, 1);
        }
    }
}
