/*
 *  Copyright 2013, Katsuhisa Maruyama (maru@jtool.org)
 */

package org.jtool.codeforest.ui.layout;

/**
 * Implements an ordered tree-map layout with a pivot by the split size.
 * @author Daiki Todoroki
 * @author Katsuhisa Maruyama
 */
public class PivotBySplitSize implements IMapLayout {
    
    public static final String Name = "Pivot by Split Size";
    
    private OrderedTreemap orderedTreemap;
    
    public PivotBySplitSize() {
        orderedTreemap = new OrderedTreemap(OrderedTreemap.PIVOT_BY_SPLIT_SIZE);
    }
        
    public void layout(IMapModel model, Rect bounds)
    {
        orderedTreemap.layout(model, bounds);
    }
    
    public String getName() {
        return Name;
    }
    
    public String getDescription() {
        return Name;
    }
    
    public void layout(IMapModel model, double x, double y, double w, double h) {
        layout(model, new Rect(x, y, w, h));
    }
}
