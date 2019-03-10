/** *****************************************************************************
 *
 *	Copyright (c) 2016 Fujitsu Services Ltd.
 *
 *	Author: Nick Battle
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

import java.io.Serializable;

import com.fujitsu.vdmj.mapper.MappedObject;

/**
 * The abstract root of all DOC nodes.
 */
abstract public class DOCNode extends MappedObject implements Serializable {

    private static final long serialVersionUID = 1L;
    public final static String MAPPINGS = "tc-doc.mappings";
    public Extent extent; // Extent of the content when formatted.

    protected DOCNode() {
        super();
    }

    // Set the extent (width and height) of a term.
    //public abstract void extent(int maxWidth);
    // Default method to allow compilation before implementation complete.
    public void extent(int maxWidth) {
    }

    // Generate the HTML format of a term.
    //public abstract String toHTML(int indent);
    // Default method to allow compilation before implementation complete.
    public String toHTML(int indent) {
        return null;
    }

    // Generate HTML format of an expression when its width is not significant.
    public String toHTML() {
        return toHTML(0);
    }
}
