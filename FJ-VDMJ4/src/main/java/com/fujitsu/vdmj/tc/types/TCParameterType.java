/*******************************************************************************
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
 ******************************************************************************/

package com.fujitsu.vdmj.tc.types;

import com.fujitsu.vdmj.lex.LexLocation;
import com.fujitsu.vdmj.tc.definitions.TCDefinition;
import com.fujitsu.vdmj.tc.definitions.TCTypeDefinition;
import com.fujitsu.vdmj.tc.lex.TCNameToken;
import com.fujitsu.vdmj.typechecker.Environment;
import com.fujitsu.vdmj.typechecker.NameScope;

public class TCParameterType extends TCType
{
	private static final long serialVersionUID = 1L;
	public final TCNameToken name;

	public TCParameterType(TCNameToken pname)
	{
		super(pname.getLocation());
		this.name = pname;
	}

	@Override
	public TCType typeResolve(Environment env, TCTypeDefinition root)
	{
		if (resolved) return this; else resolved = true;

		TCDefinition p = env.findName(name, NameScope.NAMES);

		if (p == null || !(p.getType() instanceof TCParameterType))
		{
			report(3433, "Parameter type @" + name + " not defined");
		}

		return this;
	}
	
	@Override
	public boolean isOrdered(LexLocation loc)
	{
		return true;
	}

	@Override
	public int hashCode()
	{
		return name.hashCode();
	}

	@Override
	public String toDisplay()
	{
		return "@" + name;
	}
}
