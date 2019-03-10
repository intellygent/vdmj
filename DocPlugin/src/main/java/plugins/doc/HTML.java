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

import com.fujitsu.vdmj.tc.definitions.TCDefinitionList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import plugins.doc.lex.DOCNameToken;

public class HTML
{
    // General definitions.
    public static final String INDEX_FILE = "index.html";
    public static final String MENU_FILE = "menu.html";
    public static final String SPEC_FRAME = "Specification";
    public static final String DOCTYPE = "<!DOCTYPE html>";
    public static final String CHARSET = "<meta charset=\"UTF-8\">";

    // The names of HTML tags.
    public static final String HTML_TAG = "html";
    public static final String HEAD_TAG = "head";
    public static final String TITLE_TAG = "title";
    public static final String BODY_TAG = "body";
    public static final String HEADER_TAG = "header";
    public static final String DETAILS_TAG = "details";
    public static final String SUMMARY_TAG = "summary";
    public static final String SPAN_TAG = "span";
    public static final String DIV_TAG = "div";
    public static final String SECTION_TAG = "section";
    public static final String A_TAG = "a";
    public static final String P_TAG = "p";
    public static final String H1_TAG = "h1";
    public static final String H2_TAG = "h2";
    public static final String H3_TAG = "h3";
    public static final String HR_TAG = "hr";
    public static final String UL_TAG = "ul";
    public static final String LI_TAG = "li";
    public static final String IFRAME_TAG = "iframe";
    public static final String CODE_TAG = "code";

    // The names of HTML attributes.
    public static final String ATTRIBUTE_CLASS = "class";
    public static final String ATTRIBUTE_NAME = "name";
    public static final String ATTRIBUTE_HREF = "href";
    public static final String ATTRIBUTE_TARGET = "target";
    public static final String ATTRIBUTE_ID = "id";
    public static final String ATTRIBUTE_SRC = "src";
    
    // The names of defined CSS classes.
    public static final String CLASS_LIST = "list";
    public static final String CLASS_SPEC = "spec";
    public static final String CLASS_MENU = "menu";
    public static final String CLASS_EXPORT = "export";
    public static final String CLASS_NOEXPORT = "noexport";
    public static final String CLASS_DEFINITION = "definition";
    public static final String CLASS_KEYWORD = "keyword";
    public static final String CLASS_TYPE = "type";
    public static final String CLASS_STATE = "state";
    public static final String CLASS_COMMENT = "comment";
    public static final String CLASS_VALUE = "value";
    public static final String CLASS_FUNCTION = "function";
    public static final String CLASS_OPERATION = "operation";
    public static final String CLASS_REFERENCE = "reference";
    public static final String CLASS_MODULE = "module";
    public static final String CLASS_QUOTE = "quote";

    // The names of element identifiers.
    public static final String ID_IMPORTS = "imports";
    public static final String ID_EXPORTS = "exports";
    public static final String ID_TYPES = "types";
    public static final String ID_STATE = "state";
    public static final String ID_VALUES = "values";
    public static final String ID_FUNCTIONS = "functions";
    public static final String ID_OPERATIONS = "operations";

    public static final String doctype()
    {
        return DOCTYPE;
    }
    
    public static final String head(String moduleName, String vdmCssFilename, String customCssFilename)
    {
        String result = openTag(HEAD_TAG) + title(moduleName) + CHARSET + link(vdmCssFilename);
        if (customCssFilename != null)
        {
            result += link(customCssFilename);
        }
        return result + closeTag(HEAD_TAG);
    }

    public static final String head(String moduleName, String vdmCssFilename)
    {
        return head(moduleName, vdmCssFilename, null);
    }

    public static final String title(String title)
    {
        return element(TITLE_TAG, title);
    }

    public static final String link(String filename)
    {
        return String.format("<link href=\"%s\" rel=\"stylesheet\">", filename);
    }

    /*
      Methods that create HTML elements, with attributes and content.
    */
    public static String element(String tag, String key, String value, String content)
    {
       return openTag(tag, key, value) + content + closeTag(tag);
    }

    public static String element(String tag, String key, String value)
    {
        return element(tag, key, value, "");
    }

    public static String element(String tag, String content)
    {
       return openTag(tag) + content + closeTag(tag);
    }

    public static String element(String tag)
    {
       return element(tag, "");
    }
    
    /*
      Mthods that create HTML open and close tags.
    */
    public static String openTag(String tag, Map<String, String> attr)
    {
        String result = '<' + tag;
        if (attr != null && attr.size() > 0) {
            for (String s : attr.keySet()) {
                String val = attr.get(s);
                if (val == null) {
                    result += String.format(" %s", s);
                } else {
                    result += String.format(" %s=\"%s\"", s, val);
                }
            }
        }
        return result + '>';
    }

    public static String openTag(String tag, String key, String value)
    {
        Map<String, String> attrs = new HashMap();
        attrs.put(key, value);
        return openTag(tag, attrs);
    }

    public static String openTag(String tag)
    {
        return openTag(tag, null);
    }

    public static String closeTag(String tag)
    {
        return String.format("</%s>", tag);
    }
    
    /* HTML formatted VDM keyword. */
    public static String keyword(String qt)
    {
        return element(SPAN_TAG, ATTRIBUTE_CLASS, CLASS_KEYWORD, qt);
    }

    /* HTML formatted VDM quote.*/
    public static String quote(String qt)
    {
        return element(SPAN_TAG, ATTRIBUTE_CLASS, CLASS_QUOTE, "&lt;" + qt + ">");
    }

    public static String anchor(String id, String label)
    {
        return String.format("<a %s=\"%s\">%s</a>", ATTRIBUTE_ID, id, label);
    }
    
    public static String a(DOCNameToken token, String cls)
    {
        String tt = element(SPAN_TAG, ATTRIBUTE_CLASS, "tooltiptext", token.getModule());
        return element(SPAN_TAG, ATTRIBUTE_CLASS, cls,
                       element(SPAN_TAG, ATTRIBUTE_CLASS, "tooltip",
                               a(CLASS_REFERENCE, token.getModule() + ".html" + "#" + token.getName(), SPEC_FRAME, token.getName())+tt));
    }
    
    public static String a(String classId, String href, String target, String label)
    {
        String result = "<a";
        if (classId != null) {
            result += String.format(" %s=\"%s\"", ATTRIBUTE_CLASS, classId);
        }
        result += String.format(" %s=\"%s\"", ATTRIBUTE_HREF, href);
        if (target != null) {
            result += String.format(" %s=\"%s\"", ATTRIBUTE_TARGET, target);
        }
        return result + ">" + label + closeTag(A_TAG);
    }

    public static String a(String classId, String href, String label)
    {
        return a(classId, href, null, label);
    }

    public static String a(String href, String label)
    {
        return a((String)null, href, label);
    }

    public static String openDetails(boolean open, String tag, String value)
    {
        return String.format("<%s %s %s=\"%s\">", DETAILS_TAG, open?"open":"", tag, value);
    }

    public static String openDetails(boolean open)
    {
        return String.format("<%s %s>", DETAILS_TAG, open?"open":"");
    }
    
    /*
      General formatting utilities.
    */
    public static String newline(int n)
    {
        return newline() + pad(n);
    }
    
    public static String newline()
    {
        return "\n";
    }
    
    public static String pad(int indent)
    {
        return indent == 0 ? "" : String.format("%" + indent + "c", ' ');
    }

    public static List<String> getHTMLComments(List<String> comments)
    {
        List<String> result = new ArrayList();
        for (String s : comments)
        {
            s = s.trim();
            if (s.startsWith("<"))
            {
                result.add(s);
            }
        }
        return result;
    }
    
    /*
      Create the HTML output for a term preceded by a unary operator (optionally delimited).
    */
    public static String unaryPre(int indent, String op, String pre, DOCNode node, String post, boolean newline)
    {
        String pad = Operator.isUnaryPad(op) ? " " : "";
        String op1 = Symbol.get(op);
        return word(op1) +
               (newline ? newline(indent+HTMLParameters.step) + pre + node.toHTML(indent+HTMLParameters.step+pre.length()) + post
                        : pad + pre + node.toHTML(indent+Symbol.size(op)+pad.length()+pre.length()) + post);
    }
    
    /*
      Create the HTML output for a term preceded by a unary operator (not delimited).
    */
    public static String unaryPre(int indent, String op, DOCNode node, boolean newline)
    {
        return unaryPre(indent, op, "", node, "", newline);
    }
    
    /*
      Create the HTML output for a term preceded by a unary operator (optionally delimited).
    */
    public static String unaryPre(int indent, String op, String pre, DOCMappedList list, String post, boolean newline)
    {
        String pad = Operator.isUnaryPad(op) ? " " : "";
        String op1 = Symbol.get(op);
        return word(op1) +
               (newline ? newline(indent+HTMLParameters.step) + pre + list.toHTML(indent+HTMLParameters.step+pre.length()) + post
                        : pad + pre + list.toHTML(indent+Symbol.size(op)+pad.length()+pre.length()) + post);
    }
    
    /*
      Create the HTML output for a term preceded by a unary operator (not delimited).
    */
    public static String unaryPre(int indent, String op, DOCMappedList list, boolean newline)
    {
        return unaryPre(indent, op, "", list, "", newline);
    }
    
    /*
      Create the HTML output for a term preceded by a functional term (optionally delimited).
    */
    public static String unaryPre(int indent, DOCNode func, String pre, DOCNode node, String post, boolean newline)
    {
        return func.toHTML(indent) +
               (newline ? newline(indent+HTMLParameters.step) + pre + node.toHTML(indent+HTMLParameters.step+pre.length()) + post
                        : pre + node.toHTML(indent+func.extent.numLines+pre.length()) + post);
    }
    
    /*
      Create the HTML output for a term preceded by a functional term (not delimited).
    */
    public static String unaryPre(int indent, DOCNode func, DOCNode node, boolean newline)
    {
        return unaryPre(indent, func, "", node, "", newline);
    }
    
    /*
      Create the HTML output for a term preceded by a functional term (optionally delimited).
    */
    public static String unaryPre(int indent, DOCNode func, String pre, DOCMappedList list, String post, boolean newline)
    {
        return func.toHTML(indent) +
               (newline ? newline(indent+HTMLParameters.step) + pre + list.toHTML(indent+HTMLParameters.step+pre.length()) + post
                        : pre + list.toHTML(indent+func.extent.numLines+pre.length()) + post);
    }
    
    /*
      Create the HTML output for a term preceded by a functional term (not delimited).
    */
    public static String unaryPre(int indent, DOCNode func, DOCMappedList list, boolean newline)
    {
        return unaryPre(indent, func, "", list, "", newline);
    }
    
    /*
      Create the HTML output for a term preceded by a functional term (not delimited).
    */
    public static String unaryFunc(int indent, DOCNode func, DOCMappedList list, boolean newline)
    {
        return func.toHTML(indent) +
               (newline ? newline(indent+HTMLParameters.step) + lists(indent+HTMLParameters.step, list, true)
                        : lists(indent+func.extent.numLines, list, true));
    }
    
    /*
      Create the HTML output for an infix binary term.
    */
    public static String binaryIn(int indent, DOCNode left, String op, DOCNode right, boolean newline)
    {
        String pad = Operator.isBinaryPad(op) ? " " : "";
        String op1 = Symbol.get(op);
        int step = newline ? 0 : left.extent.width + Symbol.size(op) + (pad+pad).length();
        return left.toHTML(indent) + pad + word(op1) + (newline ? newline(indent) : pad) + right.toHTML(indent+step);
    }
    
    /*
      Create the HTML output for an infix binary term, where the right hand argument is a simple identifier.
    */
    public static String binaryIn(int indent, DOCNode left, String op, String right, boolean newline)
    {
        String pad = Operator.isBinaryPad(op) ? " " : "";
        String op1 = Symbol.get(op);
        return left.toHTML(indent) + pad + word(op1) + (newline ? newline(indent) : pad) + right;
    }
    
    /*
      Create the HTML output for an infix binary term, where the left hand argument is a simple identifier.
    */
    public static String binaryIn(int indent, String left, String op, DOCNode right, boolean newline)
    {
        String pad = Operator.isBinaryPad(op) ? " " : "";
        String op1 = Symbol.get(op);
        return left + pad + word(op1) + (newline ? newline(indent+HTMLParameters.step) : pad) +
               right.toHTML(indent);
    }
    
    /*
      Create the HTML output for an infix binary term, where the first term is a list of items.
    */
    public static String binaryIn(int indent, DOCMappedList left, String op, DOCNode right, boolean newline)
    {
        String pad = Operator.isBinaryPad(op) ? " " : "";
        String op1 = Symbol.get(op);
        return left.toHTML(indent) + pad + word(op1) +
               (newline ? newline(indent) : pad) + right.toHTML(indent);
    }
    
    /*
      Create the HTML output for a functional infix binary term.
    */
    public static String binaryFunc(int indent, DOCNode left, String op, DOCNode right, boolean newline)
    {
        String pad = Operator.isBinaryPad(op) ? " " : "";
        String op1 = Symbol.get(op);
        return left.toHTML(indent) + pad + word(op1) +
               (newline ? newline(indent+HTMLParameters.step) : pad) + right.toHTML(indent+HTMLParameters.step);
    }
    
    /*
      Create the HTML output for a functional infix binary term, where the right hand argument is a simple identifier.
    */
    public static String binaryFunc(int indent, DOCNode left, String op, String right, boolean newline)
    {
        String pad = Operator.isBinaryPad(op) ? " " : "";
        String op1 = Symbol.get(op);
        return left.toHTML(indent) + pad + word(op1) +
               (newline ? newline(indent+HTMLParameters.step) : pad) + right;
    }
    
    /*
      Create the HTML output for a functional infix binary term, where the left hand argument is a simple identifier.
    */
    public static String binaryFunc(int indent, String left, String op, DOCNode right, boolean newline)
    {
        String pad = Operator.isBinaryPad(op) ? " " : "";
        String op1 = Symbol.get(op);
        return word(Symbol.get(left)) + pad + word(op1) + (newline ? newline(indent+HTMLParameters.step) : pad) +
               right.toHTML(indent+HTMLParameters.step);
    }
    
    /*
      Create the HTML output for a functional infix binary term, where the first term is a list of items.
    */
    public static String binaryFunc(int indent, DOCMappedList left, String op, DOCNode right, boolean newline)
    {
        String pad = Operator.isBinaryPad(op) ? " " : "";
        String op1 = Symbol.get(op);
        return left.toHTML(indent) + pad + word(op1) +
               (newline ? newline(indent+HTMLParameters.step) : pad) + right.toHTML(indent+HTMLParameters.step);
    }
    
    /*
      Create the HTML output for a delimited term.
    
          'pre' term 'post'
    */
    public static String delimited(int indent, String pre, DOCNode node, String post)
    {
        return pre + node.toHTML(indent+pre.length()) + post;
    }

    /*
      Create the HTML output for a delimited term containing a list of items.
    
          'pre' term-list 'post'
    */
    public static String delimited(int indent, String pre, DOCMappedList list, String post)
    {
        return pre + list.toHTML(indent+pre.length()) + post;
    }

    /*
      Create the HTML output for a delimited term.
    
          'pre' term 'in' term 'post'
    */
    public static String delimited(int indent, String pre, DOCNode node1, String in, DOCNode node2, String post, boolean newline)
    {
        String result = word(pre) + " " + node1.toHTML(indent+Symbol.size(pre)+1);
        if (newline)
        {
            result += newline(indent) + word(in) + " " + node2.toHTML(indent+Symbol.size(in)+1);
        }
        else
        {
            result += " " + word(in) + " " + node2.toHTML(indent+Symbol.size(pre)+1+node1.extent.width+1+Symbol.size(in)+1);
        }
        return result + (post.isEmpty() ? "" : " " + word(post));
    }

    /*
      Create the HTML output for a delimited term.
    */
    public static String delimited(int indent, String pre, DOCNode node1, String in, DOCNode node2, boolean newline)
    {
        return delimited(indent, pre, node1, in, node2, "", newline);
    }
    
    /*
      Create the HTML output for a delimited term.
    
          'pre' term-list 'in' term 'post'
    */
    public static String delimited(int indent, String pre, DOCMappedList list, String in, DOCNode node, String post, boolean newline)
    {
        String result = word(pre) + " " + list.toHTML(indent+Symbol.size(pre)+1);
        if (newline)
        {
            result += newline(indent) + word(in) + " " + node.toHTML(indent+Symbol.size(in)+1);
        }
        else
        {
            result += " " + word(in) + " " + node.toHTML(indent+Symbol.size(pre)+1+list.extent.width+1+Symbol.size(in)+1);
        }
        return result + (post.isEmpty() ? "" : " " + word(post));
    }

    /*
      Create the HTML output for a delimited term.
    */
    public static String delimited(int indent, String pre, DOCMappedList list, String in, DOCNode node, boolean newline)
    {
        return delimited(indent, pre, list, in, node, "", newline);
    }
    
    /*
      Create the HTML output for a delimited term.
    
          'pre' term 'in' term-list 'post'
    */
    public static String delimited(int indent, String pre, DOCNode node, String in, DOCMappedList list, String post, boolean newline)
    {
        int prePad = (pre != null && !pre.isEmpty() && Character.isLetter(pre.charAt(0)) ? 1 : 0);
        int inPad = 1;
        int postPad = (post != null && !post.isEmpty() && Character.isLetter(post.charAt(0)) ? 1 : 0);
        String result = word(pre) + pad(prePad) + node.toHTML(indent+Symbol.size(pre)+prePad) + pad(inPad) + word(in);
        if (newline)
        {
            result += newline(indent+Symbol.size(in)) + list.toHTML(indent+Symbol.size(in));
        }
        else
        {
            result +=  pad(inPad) + list.toHTML(indent+Symbol.size(pre)+prePad+node.extent.width+inPad+Symbol.size(in)+inPad);
        }
        return result + pad(postPad) + word(post);
    }

    /*
      Create the HTML output for a delimited term.
    */
    public static String delimited(int indent, String pre, DOCNode node, String in, DOCMappedList list, boolean newline)
    {
        return delimited(indent, pre, node, in, list, "", newline);
    }
    
    /*
      Create the HTML output for a delimited term.
    
          'pre' term 'in' term 'in' term 'post'
    */
    public static String delimited(int indent, String pre, DOCNode node1, String in1, DOCNode node2, String in2, DOCNode node3, String post, boolean newline, boolean newline2)
    {
        String result = (pre.isEmpty() ? "" : word(pre) + " ") + node1.toHTML(indent+Symbol.size(pre)+1);
        int used = (pre.isEmpty() ? 0 : Symbol.size(pre)+1) + node1.extent.width;
        if (newline)
        {
            result += newline(indent) + word(in1) + " " + node2.toHTML(indent+Symbol.size(in1)+1);
        }
        else
        {
            result += " " + word(in1) + " " + node2.toHTML(indent+used+1+Symbol.size(in1)+1);
            used += 1 + Symbol.size(in1) + 1 + node2.extent.width;
        }
        if (newline2)
        {
            result += newline(indent) + word(in2) + " " + node3.toHTML(indent+Symbol.size(in2)+1);
        }
        else
        {
            result += " " + word(in2) + " " + node3.toHTML(indent+used+1+Symbol.size(in2)+1);
            used += 1 + Symbol.size(in2) + 1 + node3.extent.width;
        }
        if (!post.isEmpty())
        {
            result += (newline || newline2 ? newline(indent) : " ") + word(post);
        }
        return result;
    }

    /*
      Create the HTML output for a delimited term.
    
          'pre' term 'in' term-list 'in' term 'post'
    */
    public static String delimited(int indent, String pre, DOCNode node1, String in1, DOCMappedList list, String in2, DOCNode node2, String post, boolean newline, boolean newline2)
    {
        String result = (pre.isEmpty() ? "" : word(pre) + " ") + node1.toHTML(indent+Symbol.size(pre)+1);
        int used = (pre.isEmpty() ? 0 : Symbol.size(pre)+1) + node1.extent.width;
        if (newline)
        {
            result += newline(indent) + word(in1) + " " + list.toHTML(indent+Symbol.size(in1)+1);
        }
        else
        {
            result += " " + word(in1) + " " + list.toHTML(indent+used+1+Symbol.size(in1)+1);
            used += 1 + Symbol.size(in1) + 1 + list.extent.width;
        }
        if (newline2)
        {
            result += newline(indent) + word(in2) + " " + node2.toHTML(indent+Symbol.size(in2)+1);
        }
        else
        {
            result += " " + word(in2) + " " + node2.toHTML(indent+used+1+Symbol.size(in2)+1);
            used += 1 + Symbol.size(in2) + 1 + node2.extent.width;
        }
        if (!post.isEmpty())
        {
            result += (newline || newline2 ? newline(indent) : " ") + word(post);
        }
        return result;
    }

    /*
      Create the HTML output for a delimited term.
    
          'pre' term-list 'in' term 'in' term 'post'
    */
    /*public static String delimited(int pad, String pre, DOCMappedList list, String in1, DOCNode node1, String in2, DOCNode node2, String post, boolean newline, boolean newline2)
    {
        String result = (pre.isEmpty() ? "" : word(pre) + " ") + list.toHTML(pad+Symbol.size(pre)+1);
        int used = (pre.isEmpty() ? 0 : Symbol.size(pre)+1) + list.extent.width;
        if (newline)
        {
            result += newline(pad) + word(in1) + " " + node1.toHTML(pad+Symbol.size(in1)+1);
        }
        else
        {
            result += " " + word(in1) + " " + node1.toHTML(pad+used+1+Symbol.size(in1)+1);
            used += 1 + Symbol.size(in1) + 1 + node1.extent.width;
        }
        if (newline2)
        {
            result += newline(pad) + word(in2) + " " + node2.toHTML(pad+Symbol.size(in2)+1);
        }
        else
        {
            result += " " + word(in2) + " " + node2.toHTML(pad+used+1+Symbol.size(in2)+1);
            used += 1 + Symbol.size(in2) + 1 + node2.extent.width;
        }
        if (!post.isEmpty())
        {
            result += (newline || newline2 ? newline(pad) : " ") + word(post);
        }
        return result;
    }*/

    /*
      Create the HTML output for a list of terms.
    */
    public static String list(int indent, String pre, List<DOCNode> nodes, String separator, String post, boolean newline)
    {
        String result = Symbol.get(pre);
        String pad = Operator.isBinaryPad(separator) ? " " : "";
        for (int i = 0 ; i < nodes.size() ; i++)
        {
            result += nodes.get(i).toHTML(indent+Symbol.size(pre));
            if (i < nodes.size()-1)
            {
                result += pad + Symbol.get(separator)+ (newline ? newline(indent+Symbol.size(pre)) : pad);
            }
        }
        return result + post;
    }

    /*
      Create the HTML output for a list of terms.
    */
    public static String list(int indent, List<DOCNode> nodes, String separator, boolean newline)
    {
        return list(indent, "", nodes, separator, "", newline);
    }

    public static String list(int indent, List<DOCNode> nodes)
    {
        return list(indent, nodes, "", true);
    }
    
    /*
      Create the HTML output for a list of terms list.
    */
    public static String lists(int indent, String pre, List<DOCMappedList> nodes, String separator, String post, boolean newline)
    {
        String result = Symbol.get(pre);
        String pad = Operator.isBinaryPad(separator) ? " " : "";
        for (int i = 0 ; i < nodes.size() ; i++)
        {
            result += nodes.get(i).toHTML(indent+Symbol.size(pre));
            if (i < nodes.size()-1)
            {
                result += pad + Symbol.get(separator)+ (newline ? newline(indent+Symbol.size(pre)) : pad);
            }
        }
        return result + post;
    }

    /*
      Create the HTML output for a list of term lists.
    */
    public static String lists(int indent, List<DOCMappedList> nodes, String separator, boolean newline)
    {
        return lists(indent, "", nodes, separator, "", newline);
    }

    /*
      Create the HTML output for a list of term lists.
    */
    public static String lists(int indent, List<DOCMappedList> nodes, boolean newline)
    {
        String result = "";
        for (int i = 0 ; i < nodes.size() ; i++)
        {
            boolean paren = !HTMLParameters.fargNoParen || !nodes.get(i).isPrimitive();
            result += (paren?"(":"")+nodes.get(i).toHTML(indent)+(paren?")":"");
            if (i < nodes.size()-1)
            {
                result += (newline ? newline(indent) : " ");
            }
        }
        return result;
    }

    public static String word(String word)
    {
        String s = Symbol.get(word);
        return Keyword.is(s) ? keyword(s) : s;
    }

    /*
      Create the HTML output for a quantifier term.
    
          'label' bind '&' predicate
    */
    public static String quantifier(int indent, String label, DOCNode bind, DOCNode predicate, boolean newline, boolean newline2)
    {
        String result = word(label) + (newline ? newline(indent+HTMLParameters.step) : " ");
        result += bind.toHTML(indent + (newline ? HTMLParameters.step : Symbol.size(label)+1));
        result += " " + Symbol.get("&") + (newline2 ? newline(indent+HTMLParameters.step) : " ");
        return result + predicate.toHTML(indent + (newline2 ? HTMLParameters.step : Symbol.size(label)+bind.extent.width+Symbol.size(label)+3));
    }
    
    /*
      Create the HTML output for a quantifier term.
    
          'label' bind-list '&' predicate
    */
    public static String quantifier(int indent, String label, DOCMappedList bind, DOCNode predicate, boolean newline, boolean newline2)
    {
        String result = word(label) + (newline ? newline(indent+HTMLParameters.step) : " ");
        result += bind.toHTML(indent + (newline ? HTMLParameters.step : Symbol.size(label)+1));
        result += " " + Symbol.get("&") + (newline2 ? newline(indent+HTMLParameters.step) : " ");
        return result + predicate.toHTML(indent + (newline2 ? HTMLParameters.step : Symbol.size(label)+bind.extent.width+Symbol.size(label)+3));
    }
    
    /*
      Create the HTML output for a local definition term.
    
          'let' bind-list 'in' body
    */
    /*public static String localDef(int indent, String let, TCDefinitionList bind, String in, DOCNode body)
    {
        return word(let) + " " + bind.toHTMLLocal(indent+Symbol.size(let)+1) + newline(indent) +
               word(in) + " " + body.toHTML(indent+Symbol.size(in)+1);
    }*/
}
