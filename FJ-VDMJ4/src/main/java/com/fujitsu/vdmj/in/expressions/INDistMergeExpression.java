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

package com.fujitsu.vdmj.in.expressions;

import com.fujitsu.vdmj.lex.LexLocation;
import com.fujitsu.vdmj.runtime.Context;
import com.fujitsu.vdmj.runtime.ValueException;
import com.fujitsu.vdmj.values.MapValue;
import com.fujitsu.vdmj.values.Value;
import com.fujitsu.vdmj.values.ValueMap;
import com.fujitsu.vdmj.values.ValueSet;

public class INDistMergeExpression extends INUnaryExpression
{
	private static final long serialVersionUID = 1L;

	public INDistMergeExpression(LexLocation location, INExpression exp)
	{
		super(location, exp);
	}

	@Override
	public String toString()
	{
		return "(merge " + exp + ")";
	}

	@Override
	public Value eval(Context ctxt)
	{
		breakpoint.check(location, ctxt);

		try
		{
    		ValueSet setmap = exp.eval(ctxt).setValue(ctxt);
    		ValueMap result = new ValueMap();

    		for (Value v: setmap)
    		{
    			ValueMap m = v.mapValue(ctxt);
    			
    			for (Value k: m.keySet())
    			{
    				Value rng = m.get(k);
    				Value old = result.put(k, rng);

    				if (old != null && !old.equals(rng))
    				{
    					abort(4021, "Duplicate map keys have different values: " + k, ctxt);
    				}
    			}
    		}

    		return new MapValue(result);
        }
        catch (ValueException e)
        {
        	return abort(e);
        }
    }
}
