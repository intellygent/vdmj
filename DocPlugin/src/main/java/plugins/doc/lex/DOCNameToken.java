/** *****************************************************************************
 *
 *	Copyright (c) 2019 Paul Chisholm
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
package plugins.doc.lex;

import com.fujitsu.vdmj.lex.LexLocation;
import com.fujitsu.vdmj.tc.lex.TCNameToken;

public class DOCNameToken extends DOCToken {

    private static final long serialVersionUID = 1L;
    private final TCNameToken token;
    public final LexLocation location;

    public DOCNameToken(TCNameToken token) {
        this.token = token;
        this.location = token.getLocation();
    }

    public String getName() // Simple name, never explicit
    {
        return token.getName();
    }

    public String getModule() // Module name
    {
        return token.getModule();
    }

    @Override
    public void extent(int maxWidth) {
        return;
    }

    @Override
    public String toHTML(int indent) {
        return null;
    }
}
