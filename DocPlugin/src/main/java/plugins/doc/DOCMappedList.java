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

import java.util.List;

import com.fujitsu.vdmj.mapper.MappedList;
import java.util.ArrayList;

abstract public class DOCMappedList<FROM, TO> extends MappedList<FROM, TO> {

    private static final long serialVersionUID = 1L;

    public DOCMappedList(List<FROM> from) throws Exception {
        super(DOCNode.MAPPINGS, from);
    }

    public DOCMappedList() {
        super();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof DOCMappedList) {
            return super.equals(other);
        }

        return false;
    }

    // Set the extent (width and height) of a mappped list.
    public abstract void extent(int maxWidth);

    // Generate the HTML format of a mapped list.
    public abstract String toHTML(int indent);

    // Generate HTML format of a mapped list when its width is not significant.
    public String toHTML() {
        return toHTML(0);
    }

    // Create a list of nodes from the mapped list target.
    public List<DOCNode> to() {
        List<DOCNode> result = new ArrayList();
        for (TO t : this) {
            result.add((DOCNode) t);
        }
        return result;
    }

    // Create a list of nodes from the nested mapped list target.
    public List<DOCMappedList> toList() {
        List<DOCMappedList> result = new ArrayList();
        for (TO t : this) {
            result.add((DOCMappedList) t);
        }
        return result;
    }

    // Is the list primitive; subclass specific.
    public boolean isPrimitive() {
        return false;
    }
}
