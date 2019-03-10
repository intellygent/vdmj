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

import java.util.HashMap;
import java.util.Map;

/*
  Characteristics and utility methods for handling VDM operators, Both expression
  and type operators.
*/
public class Operator
{    
    // Unary expression operators
    private static final Map<String, Characteristic> unaryOperators;
    // Binary expression operators
    private static final Map<String, Characteristic> binaryOperators;
    // Unary type operators
    private static final Map<String, Characteristic> unaryTypeOperators;
    // Binary type operators
    private static final Map<String, Characteristic> binaryTypeOperators;
        
    static
    {
        unaryOperators = new HashMap();
        unaryOperators.put("+",  new Characteristic(Associativity.Right, 406, false));
        unaryOperators.put("-",  new Characteristic(Associativity.Right, 406, false));
        unaryOperators.put("abs",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("floor",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("card",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("power",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("dinter",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("dunion",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("dom",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("rng",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("merge",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("len",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("elems",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("hd",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("tl",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("conc",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("inds",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("reverse",  new Characteristic(Associativity.Right, 406));
        unaryOperators.put("inverse",  new Characteristic(Associativity.Right, 403));
        unaryOperators.put("not",  new Characteristic(Associativity.Right, 205));
        unaryOperators.put("return",  new Characteristic(Associativity.None, 101));
        unaryOperators.put("dcl",  new Characteristic(Associativity.None, 101));
        unaryOperators.put("pre",  new Characteristic(Associativity.None, 101));
        unaryOperators.put("post",  new Characteristic(Associativity.None, 101));
        unaryOperators.put("measure",  new Characteristic(Associativity.None, 101));

        binaryOperators = new HashMap();
        binaryOperators.put("comp",  new Characteristic(Associativity.Assoc, 602));
        binaryOperators.put("**",  new Characteristic(Associativity.Left, 601));
        binaryOperators.put(".",  new Characteristic(Associativity.Left, 501, false));
        binaryOperators.put(".#",  new Characteristic(Associativity.Left, 501, false));
        binaryOperators.put("",  new Characteristic(Associativity.Left, 501, false));
        binaryOperators.put(",...,",  new Characteristic(Associativity.None, 501));
        binaryOperators.put(":>",  new Characteristic(Associativity.Left, 405));
        binaryOperators.put(":->",  new Characteristic(Associativity.Left, 405));
        binaryOperators.put("<:",  new Characteristic(Associativity.Right, 404));
        binaryOperators.put("<-:",  new Characteristic(Associativity.Right, 404));
        binaryOperators.put("*",  new Characteristic(Associativity.Assoc, 402));
        binaryOperators.put("/",  new Characteristic(Associativity.Left, 402));
        binaryOperators.put("rem",  new Characteristic(Associativity.Left, 402));
        binaryOperators.put("mod",  new Characteristic(Associativity.Left, 402));
        binaryOperators.put("div",  new Characteristic(Associativity.Left, 402));
        binaryOperators.put("inter",  new Characteristic(Associativity.Assoc, 402));
        binaryOperators.put("+",  new Characteristic(Associativity.Assoc, 401));
        binaryOperators.put("-",  new Characteristic(Associativity.Left, 401));
        binaryOperators.put("union",  new Characteristic(Associativity.Assoc, 401));
        binaryOperators.put("\\",  new Characteristic(Associativity.Left, 401));
        binaryOperators.put("munion",  new Characteristic(Associativity.Assoc, 401));
        binaryOperators.put("++",  new Characteristic(Associativity.Assoc, 401));
        binaryOperators.put("^",  new Characteristic(Associativity.Assoc, 401));
        binaryOperators.put("=",  new Characteristic(Associativity.None, 301));
        binaryOperators.put("<>",  new Characteristic(Associativity.None, 301));
        binaryOperators.put("<=",  new Characteristic(Associativity.None, 301));
        binaryOperators.put("<",  new Characteristic(Associativity.None, 301));
        binaryOperators.put(">=",  new Characteristic(Associativity.None, 301));
        binaryOperators.put(">",  new Characteristic(Associativity.None, 301));
        binaryOperators.put("subset",  new Characteristic(Associativity.None, 301));
        binaryOperators.put("psubset",  new Characteristic(Associativity.None, 301));
        binaryOperators.put("in set",  new Characteristic(Associativity.None, 301));
        binaryOperators.put("not in set",  new Characteristic(Associativity.None, 301));
        binaryOperators.put("in seq",  new Characteristic(Associativity.None, 301));
        binaryOperators.put("and",  new Characteristic(Associativity.Assoc, 204));
        binaryOperators.put("or",  new Characteristic(Associativity.Assoc, 203));
        binaryOperators.put("=>",  new Characteristic(Associativity.Right, 202));
        binaryOperators.put("<=>",  new Characteristic(Associativity.Assoc, 201));
        binaryOperators.put(",",  new Characteristic(Associativity.None, 0));
        binaryOperators.put("|->",  new Characteristic(Associativity.None, 0));
        binaryOperators.put(":=",  new Characteristic(Associativity.None, 0));
        binaryOperators.put(":",  new Characteristic(Associativity.None, 0));
        binaryOperators.put(":-",  new Characteristic(Associativity.None, 0));
        binaryOperators.put("==",  new Characteristic(Associativity.None, 0));
        binaryOperators.put("::",  new Characteristic(Associativity.None, 0));

        unaryTypeOperators = new HashMap();
        unaryTypeOperators.put("seq of",  new Characteristic(Associativity.Right, 104));
        unaryTypeOperators.put("seq1 of",  new Characteristic(Associativity.Right, 104));
        unaryTypeOperators.put("set of",  new Characteristic(Associativity.Right, 104));
        unaryTypeOperators.put("set1 of",  new Characteristic(Associativity.Right, 104));

        binaryTypeOperators = new HashMap();
        binaryTypeOperators.put("*",  new Characteristic(Associativity.Assoc, 103));
        binaryTypeOperators.put("|",  new Characteristic(Associativity.Assoc, 102));
        binaryTypeOperators.put("->",  new Characteristic(Associativity.Right, 101));
        binaryTypeOperators.put("+>",  new Characteristic(Associativity.Right, 101));
        binaryTypeOperators.put("==>",  new Characteristic(Associativity.Right, 101));
    }

    /*
      The kinds of associativity for operators.
    */
    static enum Associativity
    {
        Left,
        Right,
        Assoc,
        None
    }
    
    /*
      The characteristics of an operator.
    */
    private static class Characteristic
    {
        Associativity assoc; // Operator associativity
        Integer precedence;  // Operator precedence
        boolean pad;         // Should the operator be padded, i.e. separated from its arguments by a space
        
        Characteristic(Associativity assoc, int precedence, boolean pad)
        {
            this.assoc = assoc;
            this.precedence = precedence;
            this.pad = pad;
        }

        Characteristic(Associativity assoc, int precedence)
        {
            this(assoc, precedence, true);
        }
    }
    
    // The associativity to a binary expression operator.
    static Associativity bassoc(String op)
    {
        return binaryOperators.get(op).assoc;
    }

    // The precedence to a binary expression operator.
    static Integer bprecedence(String op)
    {
        return binaryOperators.get(op).precedence;
    }

    // The associativity to a unary expression operator.
    static Associativity uassoc(String op)
    {
        return unaryOperators.get(op).assoc;
    }

    // The precedence to a unary expression operator.
    static Integer uprecedence(String op)
    {
        return unaryOperators.get(op).precedence;
    }

    // The associativity to a binary type operator.
    static Associativity bassocType(String op)
    {
        return binaryTypeOperators.get(op).assoc;
    }

    // The precedence to a binary type operator.
    static Integer bprecedenceType(String op)
    {
        return binaryTypeOperators.get(op).precedence;
    }

    // The associativity to a unary type operator.
    static Associativity uassocType(String op)
    {
        return unaryTypeOperators.get(op).assoc;
    }

    // The precedence to a unary type operator.
    static Integer uprecedenceType(String op)
    {
        return unaryTypeOperators.get(op).precedence;
    }
    
    // Is the argument a binary operator; expression or type?
    static boolean isBinaryOperator(String op)
    {
        return binaryOperators.get(op) != null || binaryTypeOperators.get(op) != null;
    }

    // Is the argument a unary operator; expression or type?
    static boolean isUnaryOperator(String op)
    {
        return unaryOperators.get(op) != null || unaryTypeOperators.get(op) != null;
    }

    // Is the argument a unary operator that requires padding?
    public static boolean isUnaryPad(String op)
    {
        Characteristic opc = unaryOperators.get(op);
        Characteristic typec = unaryTypeOperators.get(op);
        return opc != null && opc.pad || typec != null && typec.pad;
    }

    // Is the argument a binary operator that requires padding?
    public static boolean isBinaryPad(String op)
    {
        Characteristic opc = binaryOperators.get(op);
        Characteristic typec = binaryTypeOperators.get(op);
        return opc != null && opc.pad || typec != null && typec.pad;
    }
}
