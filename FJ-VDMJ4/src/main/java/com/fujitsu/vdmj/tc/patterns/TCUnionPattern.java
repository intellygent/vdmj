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

package com.fujitsu.vdmj.tc.patterns;

import java.util.List;
import java.util.Vector;

import com.fujitsu.vdmj.lex.LexLocation;
import com.fujitsu.vdmj.tc.definitions.TCDefinitionList;
import com.fujitsu.vdmj.tc.lex.TCNameList;
import com.fujitsu.vdmj.tc.types.TCSetType;
import com.fujitsu.vdmj.tc.types.TCType;
import com.fujitsu.vdmj.tc.types.TCTypeSet;
import com.fujitsu.vdmj.tc.types.TCUnknownType;
import com.fujitsu.vdmj.typechecker.Environment;
import com.fujitsu.vdmj.typechecker.NameScope;
import com.fujitsu.vdmj.typechecker.TypeCheckException;

public class TCUnionPattern extends TCPattern
{
	private static final long serialVersionUID = 1L;
	public final TCPattern left;
	public final TCPattern right;

	public TCUnionPattern(TCPattern left, LexLocation location, TCPattern right)
	{
		super(location);
		this.left = left;
		this.right = right;
	}

	@Override
	public void unResolve()
	{
		left.unResolve();
		right.unResolve();
		resolved = false;
	}

	@Override
	public void typeResolve(Environment env)
	{
		if (resolved) return; else { resolved = true; }

		try
		{
			left.typeResolve(env);
			right.typeResolve(env);
		}
		catch (TypeCheckException e)
		{
			unResolve();
			throw e;
		}
	}

	@Override
	public String toString()
	{
		return left + " union " + right;
	}

	@Override
	public int getLength()
	{
		int llen = left.getLength();
		int rlen = right.getLength();
		return (llen == ANY || rlen == ANY) ? ANY : llen + rlen;
	}

	@Override
	public TCDefinitionList getAllDefinitions(TCType type, NameScope scope)
	{
		TCDefinitionList defs = new TCDefinitionList();

		if (!type.isSet(location))
		{
			report(3206, "Matching expression is not a set type");
		}

		defs.addAll(left.getAllDefinitions(type, scope));
		defs.addAll(right.getAllDefinitions(type, scope));

		return defs;
	}

	@Override
	public TCNameList getAllVariableNames()
	{
		TCNameList list = new TCNameList();

		list.addAll(left.getAllVariableNames());
		list.addAll(right.getAllVariableNames());

		return list;
	}

	@Override
	public TCType getPossibleType()
	{
		TCTypeSet list = new TCTypeSet();

		list.add(left.getPossibleType());
		list.add(right.getPossibleType());

		TCType s = list.getType(location);

		return s.isUnknown(location) ?
			new TCSetType(location, new TCUnknownType(location)) : s;
	}

	@Override
	public List<TCIdentifierPattern> findIdentifiers()
	{
		List<TCIdentifierPattern> list = new Vector<TCIdentifierPattern>();
		list.addAll(left.findIdentifiers());
		list.addAll(right.findIdentifiers());
		return list;
	}

	@Override
	public boolean alwaysMatches()
	{
		return left.alwaysMatches() && right.alwaysMatches();
	}
}
