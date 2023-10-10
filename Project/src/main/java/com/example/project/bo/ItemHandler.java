package com.example.project.bo;

import com.example.project.ui.ItemInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ItemHandler {
    public static Collection<ItemInfo> getItems(){
        Collection tempCollection = Item.getItems();
        ArrayList<ItemInfo> returnedItems = new ArrayList<ItemInfo>();
        for(Iterator it = tempCollection.iterator(); it.hasNext();){
            Item item = (Item) it.next();
            returnedItems.add(new ItemInfo(item.getId(), item.getName(), item.getPrice()));
        }
        return returnedItems;
    }
}
