/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
        int size = list.size();

        if(size<=1)
        {
        	return list;
        }
        List<T> first = new LinkedList<T>(list.subList(0,size/2));
        List<T> second = new LinkedList<T>(list.subList(size/2,size));
        first=mergeSort(first,comparator);
        second=mergeSort(second,comparator);
        return mergeSortHelper(first,second,comparator);
	}

	public List<T> mergeSortHelper(List<T> list1, List<T> list2 ,Comparator<T> comparator)
	{	
		List<T> mergedList = new LinkedList<T>();
		int index1=0,index2=0;
		while(index1<list1.size()||index2<list2.size())
		{
			if(index2==list2.size())
			{
				mergedList.add(list1.get(index1));
				index1++;
			}
			else if(index1==list1.size())
			{
				mergedList.add(list2.get(index2));
				index2++;	
			}
			else{
				if(comparator.compare(list1.get(index1),list2.get(index2))<0)
				{
					//System.out.println(list2.get(index2)+" -------< "+list1.get(index1));
					mergedList.add(list1.get(index1));
					index1++;
					
				}
				else{
					mergedList.add(list2.get(index2));
					index2++;
				}
			}
		}
		return mergedList;
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
        PriorityQueue<T> heap = new PriorityQueue<T>(list.size(),comparator);
        heap.addAll(list);
        list.clear();
        while(!heap.isEmpty())
        {
        	list.add(heap.poll());
        }
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
        PriorityQueue<T> heap = new PriorityQueue<T>();
        for(T temp: list)
        {
        	if(heap.size()<k)
        	{
        		heap.offer(temp);
        	}
        	else{
        		if(comparator.compare(temp,heap.peek())>0)
        		{
        			heap.poll();
        			heap.offer(temp);
        		}
        	}
        }
        list.clear();
        while(!heap.isEmpty())
        {
        	list.add(heap.poll());
        }
        return list;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
