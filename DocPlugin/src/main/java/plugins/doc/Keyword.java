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

/* 
   Defines keyword constants.
   Only includes a keyword in not already defined in com.fujitsu.vdmj.lex.Token.
*/
package plugins.doc;

import java.util.Set;
import java.util.TreeSet;

public class Keyword
{
    public static final String ABS = "abs";
    public static final String ALWAYS = "always";
    public static final String AND = "and";
    public static final String ATOMIC = "atomic";
    public static final String BE_ST = "be st";
    public static final String BOOL = "bool";
    public static final String CARD = "card";
    public static final String CASES = "cases";
    public static final String CHAR = "char";
    public static final String CONC = "conc";
    public static final String COMPOSE = "compose";
    public static final String COMP = "comp";
    public static final String DIV = "div";
    public static final String DINTER = "dinter";
    public static final String DO = "do";
    public static final String DOM = "dom";
    public static final String DUNION = "dunion";
    public static final String ELEMS = "elems";
    public static final String ELSE = "else";
    public static final String ELSEIF = "elseif";
    public static final String END = "end";
    public static final String EQ = "eq";
    public static final String ERROR = "error";
    public static final String EXISTS = "exists";
    public static final String EXISTS1 = "exists1";
    public static final String FALSE = "false";
    public static final String FLOOR = "floor";
    public static final String FORALL = "forall";
    public static final String HD = "hd";
    public static final String IF = "if";
    public static final String IN = "in";
    public static final String INDS = "inds";
    public static final String INIT = "init";
    public static final String INMAP = "inmap";
    public static final String INT = "int";
    public static final String INTER = "inter";
    public static final String INV = "inv";
    public static final String IN_SEQ = "in seq";
    public static final String IN_SET = "in set";
    public static final String INVERSE = "inverse";
    public static final String IOTA = "iota";
    public static final String ISOFCLASS = "isofclass";
    public static final String ISOFBASECLASS = "isofbaseclass";
    public static final String IS_ = "is_";
    public static final String IS_NOT_YET_SPECIFIED = "is not yet specified";
    public static final String IS_SUBCLASS_RESPONSIBILITY = "is subclass responsibility";
    public static final String LAMBDA = "lambda";
    public static final String LEN = "len";
    public static final String LET = "let";
    public static final String MAP = "map";
    public static final String MEASURE = "measure";
    public static final String MERGE = "merge";
    public static final String MK_ = "mk_";
    public static final String MOD = "mod";
    public static final String MU = "mu";
    public static final String MUNION = "munion";
    public static final String NARROW_ = "narrow";
    public static final String NAT = "nat";
    public static final String NAT1 = "nat1";
    public static final String NEW = "new";
    public static final String NIL = "nil";
    public static final String NOT = "not";
    public static final String NOT_IN_SET = "not in set";
    public static final String OBJ_ = "obj_";
    public static final String OF = "of";
    public static final String OR = "or";
    public static final String ORD = "ord";
    public static final String OTHERS = "others";
    public static final String POST = "post";
    public static final String POWER = "power";
    public static final String PRE = "pre";
    public static final String PSUBSET = "psubset";
    public static final String RAT = "rat";
    public static final String REAL = "real";
    public static final String REM = "rem";
    public static final String RESULT = "RESULT";
    public static final String RETURN = "return";
    public static final String REVERSE = "reverse";
    public static final String RNG = "rng";
    public static final String SELF = "self";
    public static final String SAMECLASS = "sameclass";
    public static final String SAMEBASECLASS = "samebaseclass";
    public static final String SEQ_OF = "seq of";
    public static final String SEQ1_OF = "seq1 of";
    public static final String SET_OF = "set of";
    public static final String SET1_OF = "set1 of";
    public static final String SKIP = "skip";
    public static final String START = "start";
    public static final String STOP = "stop";
    public static final String SUBSET = "subset";
    public static final String THEN = "then";
    public static final String THREAD = "thread";
    public static final String THREADID = "threadid";
    public static final String TIME = "time";
    public static final String TL = "tl";
    public static final String TO = "to";
    public static final String TOKEN = "token";
    public static final String TRUE = "true";
    public static final String UNDEFINED = "undefined";
    public static final String UNION = "union";
    public static final String WHILE = "while";
    
    private static final Set<String> keywords = new TreeSet();
    
    static
    {
        keywords.add(ABS);
        keywords.add(ALWAYS);
        keywords.add(AND);
        keywords.add(ATOMIC);
        keywords.add(BE_ST);
        keywords.add(BOOL);
        keywords.add(CARD);
        keywords.add(CASES);
        keywords.add(CHAR);
        keywords.add(COMP);
        keywords.add(COMPOSE);
        keywords.add(CONC);
        keywords.add(DINTER);
        keywords.add(DIV);
        keywords.add(DO);
        keywords.add(DOM);
        keywords.add(DUNION);
        keywords.add(ELEMS);
        keywords.add(ELSE);
        keywords.add(ELSEIF);
        keywords.add(END);
        keywords.add(EQ);
        keywords.add(ERROR);
        keywords.add(EXISTS);
        keywords.add(EXISTS1);
        keywords.add(FALSE);
        keywords.add(FLOOR);
        keywords.add(FORALL);
        keywords.add(HD);
        keywords.add(IF);
        keywords.add(IN);
        keywords.add(INDS);
        keywords.add(INIT);
        keywords.add(INMAP);
        keywords.add(INT);
        keywords.add(INTER);
        keywords.add(INV);
        keywords.add(IN_SEQ);
        keywords.add(IN_SET);
        keywords.add(INVERSE);
        keywords.add(IOTA);
        keywords.add(ISOFCLASS);
        keywords.add(ISOFBASECLASS);
        keywords.add(IS_);
        keywords.add(IS_NOT_YET_SPECIFIED);
        keywords.add(IS_SUBCLASS_RESPONSIBILITY);
        keywords.add(LAMBDA);
        keywords.add(LEN);
        keywords.add(LET);
        keywords.add(MAP);
        keywords.add(MEASURE);
        keywords.add(MERGE);
        keywords.add(MK_);
        keywords.add(MOD);
        keywords.add(MU);
        keywords.add(MUNION);
        keywords.add(NARROW_);
        keywords.add(NAT);
        keywords.add(NAT1);
        keywords.add(NEW);
        keywords.add(NIL);
        keywords.add(NOT);
        keywords.add(NOT_IN_SET);
        keywords.add(OBJ_);
        keywords.add(OF);
        keywords.add(OR);
        keywords.add(ORD);
        keywords.add(OTHERS);
        keywords.add(POST);
        keywords.add(POWER);
        keywords.add(PRE);
        keywords.add(PSUBSET);
        keywords.add(RAT);
        keywords.add(REAL);
        keywords.add(REM);
        keywords.add(RESULT);
        keywords.add(RETURN);
        keywords.add(REVERSE);
        keywords.add(RNG);
        keywords.add(SAMECLASS);
        keywords.add(SAMEBASECLASS);
        keywords.add(SELF);
        keywords.add(SEQ_OF);
        keywords.add(SEQ1_OF);
        keywords.add(SET_OF);
        keywords.add(SET1_OF);
        keywords.add(SKIP);
        keywords.add(START);
        keywords.add(STOP);
        keywords.add(SUBSET);
        keywords.add(THEN);
        keywords.add(THREAD);
        keywords.add(THREADID);
        //keywords.add(TIME); VDM-RT keyword
        keywords.add(TL);
        keywords.add(TO);
        keywords.add(TOKEN);
        keywords.add(TRUE);
        keywords.add(UNDEFINED);
        keywords.add(UNION);
        keywords.add(WHILE);
    }
    
    public static final boolean is(String name)
    {
        return keywords.contains(name);
    }
}
