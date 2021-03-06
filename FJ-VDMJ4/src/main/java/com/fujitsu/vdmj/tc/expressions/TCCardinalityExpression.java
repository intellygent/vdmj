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

package com.fujitsu.vdmj.tc.expressions;

import com.fujitsu.vdmj.lex.LexLocation;
import com.fujitsu.vdmj.tc.types.TCNaturalOneType;
import com.fujitsu.vdmj.tc.types.TCNaturalType;
import com.fujitsu.vdmj.tc.types.TCSet1Type;
import com.fujitsu.vdmj.tc.types.TCSetType;
import com.fujitsu.vdmj.tc.types.TCType;
import com.fujitsu.vdmj.tc.types.TCTypeList;
import com.fujitsu.vdmj.typechecker.Environment;
import com.fujitsu.vdmj.typechecker.NameScope;

public class TCCardinalityExpression extends TCUnaryExpression
{
	private static final long serialVersionUID = 1L;

	public TCCardinalityExpression(LexLocation location, TCExpression exp)
	{
		super(location, exp);
	}

	@Override
	public String toString()
	{
		return "(card " + exp + ")";
	}

	@Override
	public TCType typeCheck(Environment env, TCTypeList qualifiers, NameScope scope, TCType constraint)
	{
		TCType etype = exp.typeCheck(env, null, scope, null);
		boolean set1 = false;
		
		if (!etype.isSet(location))
		{
			exp.report(3067, "Argument of 'card' is not a set");
		}
		else
		{
			TCSetType st = etype.getSet();
			set1 = st instanceof TCSet1Type;
		}

		return possibleConstraint(constraint,
				set1 ? new TCNaturalOneType(location) : new TCNaturalType(location));
	}
}
