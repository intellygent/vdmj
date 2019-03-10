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

import java.util.List;

/*
   The extent (number of lines and number of columns) of a VDM syntactic element.
   In some situations also width to determine if a sub-element is on a new line.
 */
public class Extent
{

    public int numLines;     // The number of lines
    public int width;        // The width (number of columns)
    public boolean newline;  // Should sub term be on a new line - only used in limited situations
    public boolean newline2; // Second sub term on newline.
    
    public Extent(int numLines, int width, boolean newline, boolean newline2)
    {
        this.numLines = numLines;
        this.width = width;
        this.newline = newline;
        this.newline2 = newline2;
    }

    public Extent(int numLines, int width, boolean newline)
    {
        this(numLines, width, newline, false);
    }

    public Extent(int numLines, int width)
    {
        this(numLines, width, false);
    }

    public Extent(int width)
    {
        this(1, width);
    }

    @Override
    public String toString()
    {
        return "[" + numLines + "," + width + (newline ? ",newline" : "") + "]";
    }

    /*
      Calculate the extent for a term preceded by a unary operator (optionally delimited).
    */
    public static Extent unaryPre(int maxWidth, String op, String pre, DOCNode node, String post)
    {
        int pad = Operator.isUnaryPad(op) ? 1 : 0;
        node.extent(maxWidth - (Symbol.size(op)+pad+pre.length()+post.length()));
        if (node.extent.numLines > 1 && HTMLParameters.step+2 < Symbol.size(op))
        {
            // Argument expression requires more than one line.
            Extent e = node.extent;
            node.extent(maxWidth - (HTMLParameters.step+pre.length()+post.length()));
            if (node.extent.numLines < e.numLines)
            {
                // Argument expression requires fewer lines if not on same line as operator.
                // Argument expression starts on next line and indented.
                return new Extent(node.extent.numLines+1,
                                  Math.max(Symbol.size(op), node.extent.width+HTMLParameters.step+pre.length()+post.length()),
                                  true);
            }
            node.extent = e;
        }
        // Argument expression starts on same line as operator.
        return new Extent(node.extent.numLines, node.extent.width+Symbol.size(op)+pad+pre.length()+post.length());
    }

    /*
      Calculate the extent for a term preceded by a unary operator (undelimited).
    */
    public static Extent unaryPre(int maxWidth, String op, DOCNode node)
    {
        return unaryPre(maxWidth, op, "", node, "");
    }
    
    /*
      Calculate the extent for a term preceded by a unary operator, where the argument is a list of terms (optionally delimited).
    */
    public static Extent unaryPre(int maxWidth, String op, String pre, DOCMappedList list, String post)
    {
        int pad = Operator.isUnaryPad(op) ? 1 : 0;
        list.extent(maxWidth - (Symbol.size(op)+pad+pre.length()+post.length()));
        if (list.extent.numLines > 1 && HTMLParameters.step+2 < Symbol.size(op))
        {
            // Argument expression requires more than one line.
            Extent e = list.extent;
            list.extent(maxWidth - (HTMLParameters.step+pre.length()+post.length()));
            if (list.extent.numLines < e.numLines)
            {
                // Argument expression requires fewer lines if not on same line as operator.
                // Argument expression starts on next line and indented.
                return new Extent(list.extent.numLines+1,
                                  Math.max(Symbol.size(op), list.extent.width+HTMLParameters.step+pre.length()+post.length()),
                                  true);
            }
            list.extent = e;
        }
        // Argument expression starts on same line as operator.
        return new Extent(list.extent.numLines, list.extent.width+Symbol.size(op)+pad+pre.length()+post.length());
    }

    /*
      Calculate the extent for a term preceded by a unary operator (undelimited).
    */
    public static Extent unaryPre(int maxWidth, String op, DOCMappedList list)
    {
        return unaryPre(maxWidth, op, "", list, "");
    }
    
    /*
      Calculate the extent for a term preceded by a functional term (optionally delimited).
    */
    public static Extent unaryPre(int maxWidth, DOCNode func, String pre, DOCNode node, String post)
    {
        func.extent(maxWidth);
        if (func.extent.numLines == 1)
        {
            node.extent(maxWidth - (func.extent.width+pre.length()+post.length()));
            if (node.extent.numLines == 1 && func.extent.numLines+node.extent.numLines+pre.length()+post.length() <= maxWidth)
            {
                return new Extent(func.extent.numLines+node.extent.numLines+pre.length()+post.length());
            }
        }
        node.extent(maxWidth - (HTMLParameters.step+pre.length()+post.length()));
        return new Extent(func.extent.numLines+node.extent.numLines,
                          Math.max(func.extent.width, node.extent.width+HTMLParameters.step+pre.length()+post.length()),
                          true);
    }

    /*
      Calculate the extent for a term preceded by a functional term (undelimited).
    */
    public static Extent unaryPre(int maxWidth, DOCNode func, DOCNode node)
    {
        return unaryPre(maxWidth, func, "", node, "");
    }
    
    /*
      Calculate the extent for a term preceded by a functional term, where the argument is a list of terms (optionally delimited).
    */
    public static Extent unaryPre(int maxWidth, DOCNode func, String pre, DOCMappedList list, String post)
    {
        func.extent(maxWidth);
        if (func.extent.numLines == 1)
        {
            list.extent(maxWidth - (func.extent.width+pre.length()+post.length()));
            if (list.extent.numLines == 1 && func.extent.numLines+list.extent.numLines+pre.length()+post.length() <= maxWidth)
            {
                return new Extent(func.extent.numLines+list.extent.numLines+pre.length()+post.length());
            }
        }
        list.extent(maxWidth - (HTMLParameters.step+pre.length()+post.length()));
        return new Extent(func.extent.numLines+list.extent.numLines,
                          Math.max(func.extent.width, list.extent.width+HTMLParameters.step+pre.length()+post.length()),
                          true);
    }

    /*
      Calculate the extent for a term preceded by a functional term (undelimited).
    */
    public static Extent unaryPre(int maxWidth, DOCNode func, DOCMappedList list)
    {
        return unaryPre(maxWidth, func, "", list, "");
    }
    
    /*
      Calculate the extent for an infix binary term.
    */
    public static Extent binaryIn(int maxWidth, DOCNode left, String op, DOCNode right)
    {
        int pad = Operator.isBinaryPad(op) ? 1 : 0;
        left.extent(maxWidth);
        if (left.extent.numLines == 1 && left.extent.width+pad+Symbol.size(op) < maxWidth)
        {
            right.extent(maxWidth - (left.extent.width + Symbol.size(op) + 2*pad));
            if (right.extent.numLines == 1 && left.extent.width + Symbol.size(op) + 2*pad + right.extent.width <= maxWidth)
            {
                return new Extent(left.extent.width + Symbol.size(op) + 2*pad + right.extent.width);
            }
        }
        left.extent(maxWidth-(Symbol.size(op)+pad));
        right.extent(maxWidth);
        return new Extent(left.extent.numLines + right.extent.numLines,
                          Math.max(left.extent.width+Symbol.size(op)+pad, right.extent.width),
                          true);
    }

    /*
      Calculate the extent for an infix binary term, where the right hand argument is a simple identifier.
    */
    public static Extent binaryIn(int maxWidth, DOCNode left, String op, String right)
    {
        int pad = Operator.isBinaryPad(op) ? 1 : 0;
        left.extent(maxWidth);
        if (left.extent.numLines == 1)
        {
            if (left.extent.width + Symbol.size(op) + 2*pad + right.length() <= maxWidth)
            {
                return new Extent(left.extent.width + Symbol.size(op) + 2*pad + right.length());
            }
        }
        left.extent(maxWidth-(Symbol.size(op)+pad));
        return new Extent(left.extent.numLines+1,
                          Math.max(left.extent.width+Symbol.size(op)+pad, right.length()),
                          true);
    }

    /*
      Calculate the extent for an infix binary term, where the left hand argument is a simple identifier.
    */
    public static Extent binaryIn(int maxWidth, String left, String op, DOCNode right)
    {
        int pad = Operator.isBinaryPad(op) ? 1 : 0;
        right.extent(maxWidth - (left.length() + Symbol.size(op) + 2*pad));
        if (right.extent.numLines == 1 && left.length() + Symbol.size(op) + 2*pad + right.extent.width <= maxWidth)
        {
            return new Extent(left.length() + Symbol.size(op) + 2*pad + right.extent.width);
        }
        right.extent(maxWidth);
        return new Extent(right.extent.numLines+1,
                          Math.max(left.length()+pad+Symbol.size(op), right.extent.width),
                          true);
    }

    /*
      Calculate the extent for an infix binary term.
    */
    public static Extent binaryIn(int maxWidth, DOCMappedList left, String op, DOCNode right)
    {
        int pad = Operator.isBinaryPad(op) ? 1 : 0;
        left.extent(maxWidth);
        if (left.extent.numLines == 1)
        {
            right.extent(maxWidth - (left.extent.width + Symbol.size(op) + 2*pad));
            if (right.extent.numLines == 1 && left.extent.width + Symbol.size(op) + 2*pad + right.extent.width <= maxWidth)
            {
                return new Extent(left.extent.width + Symbol.size(op) + 2*pad + right.extent.width);
            }
        }
        left.extent(maxWidth-(Symbol.size(op)+pad));
        right.extent(maxWidth);
        return new Extent(left.extent.numLines + right.extent.numLines,
                          Math.max(left.extent.width+Symbol.size(op)+pad, right.extent.width),
                          true);
    }

    /*
      Calculate the extent for a functional infix binary term.
    */
    public static Extent binaryFunc(int maxWidth, DOCNode left, String op, DOCNode right)
    {
        int pad = Operator.isBinaryPad(op) ? 1 : 0;
        left.extent(maxWidth);
        if (left.extent.numLines == 1)
        {
            right.extent(maxWidth - (left.extent.width + Symbol.size(op) + 2*pad));
            if (right.extent.numLines == 1 && left.extent.width + Symbol.size(op) + 2*pad + right.extent.width <= maxWidth)
            {
                return new Extent(left.extent.width + Symbol.size(op) + 2*pad + right.extent.width);
            }
        }
        left.extent(maxWidth-(Symbol.size(op)+pad));
        right.extent(maxWidth-HTMLParameters.step);
        return new Extent(left.extent.numLines + right.extent.numLines,
                          Math.max(left.extent.width+Symbol.size(op)+pad, right.extent.width+HTMLParameters.step),
                          true);
    }

    /*
      Calculate the extent for a functional infix binary term, where the right hand argument is a simple identifier.
    */
    public static Extent binaryFunc(int maxWidth, DOCNode left, String op, String right)
    {
        int pad = Operator.isBinaryPad(op) ? 1 : 0;
        left.extent(maxWidth);
        if (left.extent.numLines == 1)
        {
            if (left.extent.width + Symbol.size(op) + 2*pad + right.length() <= maxWidth)
            {
                return new Extent(left.extent.width + Symbol.size(op) + 2*pad + right.length());
            }
        }
        left.extent(maxWidth-(Symbol.size(op)+pad));
        return new Extent(left.extent.numLines+1,
                          Math.max(left.extent.width+Symbol.size(op)+pad, right.length()+HTMLParameters.step),
                          true);
    }

    /*
      Calculate the extent for a functional infix binary term, where the left hand argument is a simple identifier.
    */
    public static Extent binaryFunc(int maxWidth, String left, String op, DOCNode right)
    {
        int pad = Operator.isBinaryPad(op) ? 1 : 0;
        right.extent(maxWidth - (Symbol.size(left) + Symbol.size(op) + 2*pad));
        if (right.extent.numLines == 1 && Symbol.size(left) + Symbol.size(op) + 2*pad + right.extent.width <= maxWidth)
        {
            return new Extent(Symbol.size(left) + Symbol.size(op) + 2*pad + right.extent.width);
        }
        right.extent(maxWidth-HTMLParameters.step);
        return new Extent(right.extent.numLines+1,
                          Math.max(Symbol.size(left)+pad+Symbol.size(op), right.extent.width+HTMLParameters.step),
                          true);
    }

    /*
      Calculate the extent for a functional infix binary term, where the left hand argument is a term list.
    */
    public static Extent binaryFunc(int maxWidth, DOCMappedList left, String op, DOCNode right)
    {
        int pad = Operator.isBinaryPad(op) ? 1 : 0;
        left.extent(maxWidth);
        if (left.extent.numLines == 1)
        {
            right.extent(maxWidth - (left.extent.width + Symbol.size(op) + 2*pad));
            if (right.extent.numLines == 1 && left.extent.width + Symbol.size(op) + 2*pad + right.extent.width <= maxWidth)
            {
                return new Extent(left.extent.width + Symbol.size(op) + 2*pad + right.extent.width);
            }
        }
        right.extent(maxWidth-HTMLParameters.step);
        return new Extent(left.extent.numLines + right.extent.numLines,
                          Math.max(left.extent.width, right.extent.width+HTMLParameters.step),
                          true);
    }
    
    /*
      Calculate the extent for a delimited term.
    
          'pre' term 'post'
    */
    public static Extent delimited(int maxWidth, String pre, DOCNode node, String post)
    {
        node.extent(maxWidth - (pre.length()+post.length()));
        return new Extent(node.extent.numLines, node.extent.width+pre.length()+post.length());
    }
    
    /*
      Calculate the extent for a delimited term containing a list of items.
    
          'pre' term-list 'post'
    */
    public static Extent delimited(int maxWidth, String pre, DOCMappedList list, String post)
    {
        list.extent(maxWidth - (pre.length()+post.length()));
        return new Extent(list.extent.numLines, list.extent.width+pre.length()+post.length());
    }
    
    /*
      Calculate the extent for a delimited term.
    
          'pre' term 'in' term 'post'
    */
    public static Extent delimited(int maxWidth, String pre, DOCNode node1, String in, DOCNode node2, String post)
    {
        node1.extent(maxWidth - (Symbol.size(pre)+1));
        if (node1.extent.numLines == 1)
        {
            node2.extent(maxWidth - (Symbol.size(pre)+1+node1.extent.width+1+Symbol.size(in)+1));
            if (node2.extent.numLines == 1 && Symbol.size(pre)+1+node1.extent.width+1+Symbol.size(in)+1+node2.extent.width+Symbol.size(post) <= maxWidth)
            {
                return new Extent(Symbol.size(pre)+1+node1.extent.width+1+Symbol.size(in)+1+node2.extent.width+Symbol.size(post));
            }
        }
        node2.extent(maxWidth - (Symbol.size(in)+1));
        return new Extent(node1.extent.numLines+node2.extent.numLines+(post.isEmpty()?0:1),
                          Math.max(Symbol.size(pre)+1+node1.extent.width, Symbol.size(in)+1+node2.extent.width),
                          true);
    }
    
    /*
      Calculate the extent for a delimited term.
    */
    public static Extent delimited(int maxWidth, String pre, DOCNode node1, String in, DOCNode node2)
    {
        return delimited(maxWidth, pre, node1, in, node2, "");
    }
    
    /*
      Calculate the extent for a delimited term.
    
          'pre' term-list 'in' term 'post'
    */
    public static Extent delimited(int maxWidth, String pre, DOCMappedList list, String in, DOCNode node, String post)
    {
        list.extent(maxWidth - (Symbol.size(pre)+1));
        if (list.extent.numLines == 1)
        {
            node.extent(maxWidth - (Symbol.size(pre)+1+list.extent.width+1+Symbol.size(in)+1));
            if (node.extent.numLines == 1 && Symbol.size(pre)+1+list.extent.width+1+Symbol.size(in)+1+node.extent.width+Symbol.size(post) <= maxWidth)
            {
                return new Extent(Symbol.size(pre)+1+list.extent.width+1+Symbol.size(in)+1+node.extent.width+Symbol.size(post));
            }
        }
        node.extent(maxWidth - (Symbol.size(in)+1));
        return new Extent(list.extent.numLines+node.extent.numLines+(post.isEmpty()?0:1),
                          Math.max(Symbol.size(pre)+1+list.extent.width, Symbol.size(in)+1+node.extent.width),
                          true);
    }
    
    /*
      Calculate the extent for a delimited term.
    */
    public static Extent delimited(int maxWidth, String pre, DOCMappedList list, String in, DOCNode node)
    {
        return delimited(maxWidth, pre, list, in, node, "");
    }
    
    /*
      Calculate the extent for a delimited term.
    
          'pre' term 'in' term-list 'post'
    */
    public static Extent delimited(int maxWidth, String pre, DOCNode node, String in, DOCMappedList list, String post)
    {
        int prePad = (pre != null && !pre.isEmpty() && Character.isLetter(pre.charAt(0)) ? 1 : 0);
        int inPad = 1;
        int postPad = (post != null && !post.isEmpty() && Character.isLetter(post.charAt(0)) ? 1 : 0);
        node.extent(maxWidth - (Symbol.size(pre)+prePad));
        if (node.extent.numLines == 1)
        {
            list.extent(maxWidth - (Symbol.size(pre)+prePad+node.extent.width+Symbol.size(in)+2*inPad));
            if (list.extent.numLines == 1 && Symbol.size(pre)+prePad+node.extent.width+Symbol.size(in)+2*inPad+list.extent.width+Symbol.size(post) <= maxWidth)
            {
                return new Extent(Symbol.size(pre)+prePad+node.extent.width+1+Symbol.size(in)+1+list.extent.width+Symbol.size(post));
            }
        }
        list.extent(maxWidth - (Symbol.size(pre)+prePad));
        return new Extent(node.extent.numLines+list.extent.numLines+postPad,
                          Symbol.size(pre)+prePad+Math.max(node.extent.width, list.extent.width),
                          true);
    }
    
    /*
      Calculate the extent for a delimited term.
    */
    public static Extent delimited(int maxWidth, String pre, DOCNode node, String in, DOCMappedList list)
    {
        return delimited(maxWidth, pre, node, in, list, "");
    }
    
    /*
      Calculate the extent for a delimited term.
    
          'pre' term 'in' term 'in' term 'post'
    */
    public static Extent delimited(int maxWidth, String pre, DOCNode node1, String in1, DOCNode node2, String in2, DOCNode node3, String post)
    {
        int used = pre.isEmpty() ? 0 : Symbol.size(pre)+1;
        node1.extent(maxWidth - used);
        if (node1.extent.numLines == 1)
        {
            used += node1.extent.width+1+Symbol.size(in1)+1;
            node2.extent(maxWidth - used);
            if (node2.extent.numLines == 1)
            {
                int used2 = used + node2.extent.width+1+Symbol.size(in2);
                used += node2.extent.width+1+Symbol.size(in2)+1;
                node3.extent(maxWidth - used);
                used += node3.extent.width;
                if (node3.extent.numLines == 1 && used+Symbol.size(post) <= maxWidth)
                {
                    return new Extent(used+Symbol.size(post));
                }
                if (used2 <= maxWidth)
                {
                    node3.extent(maxWidth-HTMLParameters.step);
                    return new Extent(node3.extent.numLines+1, Math.max(used, node3.extent.width+HTMLParameters.step), false, true);
                }
            }
        }
        node2.extent(maxWidth - (Symbol.size(in1)+1));
        node3.extent(maxWidth - (Symbol.size(in2)+1));
        return new Extent(node1.extent.numLines+node2.extent.numLines+node3.extent.numLines+(post.isEmpty()?0:1),
                          Math.max(Symbol.size(pre)+1+node1.extent.width,
                                   Math.max(Symbol.size(in1)+1+node2.extent.width,
                                            Symbol.size(in2)+1+node3.extent.width)),
                          true, true);
    }
    
    /*
      Calculate the extent for a delimited term.
    
          'pre' term 'in' term-list 'in' term 'post'
    */
    public static Extent delimited(int maxWidth, String pre, DOCNode node1, String in1, DOCMappedList list, String in2, DOCNode node2, String post)
    {
        int used = pre.isEmpty() ? 0 : Symbol.size(pre)+1;
        node1.extent(maxWidth - used);
        if (node1.extent.numLines == 1)
        {
            used += node1.extent.width+1+Symbol.size(in1)+1;
            list.extent(maxWidth - used);
            if (list.extent.numLines == 1)
            {
                int used2 = used + list.extent.width+1+Symbol.size(in2);
                used += list.extent.width+1+Symbol.size(in2)+1;
                node2.extent(maxWidth - used);
                used += node2.extent.width;
                if (node2.extent.numLines == 1 && used+Symbol.size(post) <= maxWidth)
                {
                    return new Extent(used+Symbol.size(post));
                }
                if (used2 <= maxWidth)
                {
                    node2.extent(maxWidth-HTMLParameters.step);
                    return new Extent(node2.extent.numLines+1, Math.max(used, node2.extent.width+HTMLParameters.step), false, true);
                }
            }
        }
        list.extent(maxWidth - (Symbol.size(in1)+1));
        node2.extent(maxWidth - (Symbol.size(in2)+1));
        return new Extent(node1.extent.numLines+list.extent.numLines+node2.extent.numLines+(post.isEmpty()?0:1),
                          Math.max(Symbol.size(pre)+1+node1.extent.width,
                                   Math.max(Symbol.size(in1)+1+list.extent.width,
                                            Symbol.size(in2)+1+node2.extent.width)),
                          true, true);
    }
    
    /*
      Calculate the extent for a delimited list of terms.
    */
    public static Extent list(int maxWidth, String pre, List<DOCNode> nodes, String separator, String post, boolean newline)
    {
        int preSize = Symbol.size(pre);
        int postSize = Symbol.size(post);
        int sepSize = Symbol.size(separator);
        int pad = (Operator.isBinaryPad(separator) ? 2 : 0);
        if (!newline)
        {
            int lines = 0;
            int width = preSize;
            for (int i = 0 ; i < nodes.size() ; i++)
            {
                nodes.get(i).extent(maxWidth-width);
                lines += nodes.get(i).extent.numLines;
                width += nodes.get(i).extent.width;
                if (i < nodes.size()-1)
                {
                    width += sepSize + pad;
                }
            }
            width += postSize;
            if (lines == nodes.size() && width <= maxWidth)
            {
                return new Extent(width);
            }
        }
        Extent extent = new Extent(0, 0, !nodes.isEmpty());
        for (int i = 0 ; i < nodes.size() ; i++)
        {
            nodes.get(i).extent(maxWidth-preSize);
            extent.numLines += nodes.get(i).extent.numLines;
            extent.width = Math.max(extent.width, nodes.get(i).extent.width+sepSize);
        }
        return extent;
    }

    /*
      Calculate the extent for a list of terms.
    */
    public static Extent list(int maxWidth, List<DOCNode> nodes, String separator, boolean newline)
    {
        return list(maxWidth, "", nodes, separator==null ? "" : separator, "", newline);
    }

    public static Extent list(int maxWidth, List<DOCNode> nodes)
    {
        return list(maxWidth, nodes, "", true);
    }
    
    /*
      Calculate the extent for a delimited list of term lists.
    */
    public static Extent lists(int maxWidth, String pre, List<DOCMappedList> nodes, String separator, String post, boolean newline)
    {
        int preSize = Symbol.size(pre);
        int postSize = Symbol.size(post);
        int sepSize = Symbol.size(separator);
        int pad = (Operator.isBinaryPad(separator) ? 2 : 0);
        if (!newline)
        {
            int lines = 0;
            int width = preSize;
            for (int i = 0 ; i < nodes.size() ; i++)
            {
                nodes.get(i).extent(maxWidth-width);
                lines += nodes.get(i).extent.numLines;
                width += nodes.get(i).extent.width;
                if (i < nodes.size()-1)
                {
                    width += sepSize + pad;
                }
            }
            width += postSize;
            if (lines == nodes.size() && width <= maxWidth)
            {
                return new Extent(width);
            }
        }
        Extent extent = new Extent(0, 0, !nodes.isEmpty());
        for (int i = 0 ; i < nodes.size() ; i++)
        {
            nodes.get(i).extent(maxWidth-preSize);
            extent.numLines += nodes.get(i).extent.numLines;
            extent.width = Math.max(extent.width, nodes.get(i).extent.width+sepSize);
        }
        return extent;
    }

    /*
      Calculate the extent for a list of term lists.
    */
    public static Extent lists(int maxWidth, List<DOCMappedList> nodes, String separator, boolean newline)
    {
        return lists(maxWidth, "", nodes, separator==null ? "" : separator, "", newline);
    }

    public static Extent lists(int maxWidth, List<DOCMappedList> nodes)
    {
        return lists(maxWidth, nodes, "", true);
    }
    
    /*
      Calculate the extent for a quantifier term.
    
          'label' bind '&' predicate
    */
    public static Extent quantifier(int maxWidth, String label, DOCNode bind, DOCNode predicate)
    {
        boolean newline = false;
        boolean newline2 = false;
        bind.extent(maxWidth-(Symbol.size(label)+1));
        if (bind.extent.numLines == 1)
        {
            predicate.extent(maxWidth-(Symbol.size(label)+bind.extent.width+Symbol.size("&")+3));
            if (predicate.extent.numLines == 1 && Symbol.size(label)+bind.extent.width+Symbol.size("&")+predicate.extent.width+3 <= maxWidth)
            {
                return new Extent(Symbol.size(label)+bind.extent.width+Symbol.size("&")+predicate.extent.width+3);
            }
        }
        if (bind.extent.numLines > 1)
        {
            Extent e = bind.extent;
            bind.extent(maxWidth-HTMLParameters.step);
            if (bind.extent.numLines < e.numLines)
            {
                newline = true;
            }
            else
            {
                bind.extent = e;
            }
        }
        predicate.extent(maxWidth-HTMLParameters.step);
        return new Extent(bind.extent.numLines+(newline?1:0)+predicate.extent.numLines,
                          Math.max((newline ? HTMLParameters.step : Symbol.size(label)+1) + bind.extent.width,
                                   predicate.extent.width+HTMLParameters.step),
                          newline, true);
    }

    /*
      Calculate the extent for a quantifier term.
    
          'label' bind-list '&' predicate
    */
    public static Extent quantifier(int maxWidth, String label, DOCMappedList bind, DOCNode predicate)
    {
        boolean newline = false;
        boolean newline2 = false;
        bind.extent(maxWidth-(Symbol.size(label)+1));
        if (bind.extent.numLines == 1)
        {
            predicate.extent(maxWidth-(Symbol.size(label)+bind.extent.width+Symbol.size("&")+3));
            if (predicate.extent.numLines == 1 && Symbol.size(label)+bind.extent.width+Symbol.size("&")+predicate.extent.width+3 <= maxWidth)
            {
                return new Extent(Symbol.size(label)+bind.extent.width+Symbol.size("&")+predicate.extent.width+3);
            }
        }
        if (bind.extent.numLines > 1)
        {
            Extent e = bind.extent;
            bind.extent(maxWidth-HTMLParameters.step);
            if (bind.extent.numLines < e.numLines)
            {
                newline = true;
            }
            else
            {
                bind.extent = e;
            }
        }
        predicate.extent(maxWidth-HTMLParameters.step);
        return new Extent(bind.extent.numLines+(newline?1:0)+predicate.extent.numLines,
                          Math.max((newline ? HTMLParameters.step : Symbol.size(label)+1) + bind.extent.width,
                                   predicate.extent.width+HTMLParameters.step),
                          newline, true);
    }
    
    /*
      Calculate the extent for a local definition term.
    
          'let' bind-list 'in' body
    */
    public static Extent localDef(int maxWidth, String let, DOCMappedList bind, String in, DOCNode body)
    {
        bind.extent(maxWidth-(Symbol.size(let)+1));
        body.extent(maxWidth-(Symbol.size(in)+1));
        return new Extent(bind.extent.numLines+body.extent.numLines,
                          Math.max(Symbol.size(let)+1+bind.extent.width,
                                   Symbol.size(in)+1+body.extent.width),
                          true);
    }
    
    /*
      Increase this extent by another extent, with padding.
    */
    public void add(Extent thatExtent, int linePad, int thisPad, int thatPad)
    {
        numLines += thatExtent.numLines+linePad;
        width = Math.max(width+thisPad, thatExtent.width+thatPad);
    }

    public void add(Extent thatExtent, int thisPad, int thatPad)
    {
        add(thatExtent, 0, thisPad, thatPad);
    }

    public void add(Extent thatExtent)
    {
        add(thatExtent, 0, 0);
    }
    
    public void addLines(int lines)
    {
        numLines += lines;
    }
}
