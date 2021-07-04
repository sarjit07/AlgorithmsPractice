
package justforcoding;

import java.io.*;
import java.util.*;




public class PriorityQueueImpl {
	public static void main(String args[]) {

		// Default: min-heap 
		PriorityQueue<Integer> pq = new PriorityQueue();
		
		pq.add(6);
		pq.add(3);
		pq.add(2);
		pq.add(10);
		pq.add(5);
		
		System.out.println(pq);
		
		System.out.println("GetMin -> " + pq.peek());
		System.out.println("Removing 2 -> " + pq.remove(2));
		
		System.out.println(pq);
		pq.add(1);
		
		System.out.println("GetMin -> " + pq.peek());
		System.out.println();
		
		
// Finding k largest elements using Priority Queue as max-heap in klogn time
		
		int a[] = {1,23,12,9,30,2,50};
		int k = 3;
		
		PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		for(int i=0;i<a.length;i++) {
			maxPQ.add(a[i]);
		}
		
		for(int i=0;i<k;i++) {
			System.out.print(maxPQ.poll() + " ");
		}
		System.out.println();
		
		
// Finding k largest elements using Priority Queue as min-heap in (n-k)logk time
		
		int a1[] = {1,23,12,9,30,2,50, 67, 34, 48, 47, 90, 100, 123, 342};
		int k1 = 3;
		PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>();
		
		for(int i=0;i<k1;i++) {
			minPQ.add(a1[i]);
		}
		
		for(int i=k1;i<a1.length;i++) {
			if(a1[i] > minPQ.peek()) {
				minPQ.poll();
				minPQ.add(a1[i]);
			}
		}
		
		Iterator iterator = minPQ.iterator();
		 
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        
        System.out.println();
        
//Finding top K students (with highest marks) in the class. as max-heap
		
        int k2 = 4;
        int n = 5;
        System.out.println();
        
        SorterDescendingMarks sorter = new SorterDescendingMarks();
        PriorityQueue<Student> pqMaxHeap = new PriorityQueue<Student>(sorter);
        
        pqMaxHeap.add(new Student(1, 89));
        pqMaxHeap.add(new Student(2, 67));
        pqMaxHeap.add(new Student(3, 43));
        pqMaxHeap.add(new Student(4, 75));
        pqMaxHeap.add(new Student(5, 100));
        pqMaxHeap.add(new Student(6, 89));
        
        
        for(int i=0;i<k2;i++) {
        	Student s = pqMaxHeap.poll();
			System.out.println( s.id+ " "+ s.marks);
		}

//Finding top K students (with highest marks) in the class. as max-heap with NAME
		
        int k3 = 4;
        int n1 = 5;
        System.out.println();
        
        SorterDescendingMarks2 sorter2 = new SorterDescendingMarks2();
        PriorityQueue<Student> pqMaxHeap2 = new PriorityQueue<Student>(sorter2);
        
        pqMaxHeap2.add(new Student("ahmed", 89));
        pqMaxHeap2.add(new Student("saras", 67));
        pqMaxHeap2.add(new Student("raj", 43));
        pqMaxHeap2.add(new Student("priyank", 75));
        pqMaxHeap2.add(new Student("abhi", 100));
        pqMaxHeap2.add(new Student("aaaa", 89));
        
        
        for(int i=0;i<k3;i++) {
        	Student s = pqMaxHeap2.poll();
			System.out.println( s.name+ " "+ s.marks);
		}

		

	}
	
	

}

class SorterDescendingMarks implements Comparator<Student>{
	
	@Override
	public int compare(Student o1, Student o2) {
		
		if(o1.marks == o2.marks)
			return o1.id - o2.id; 		// Asecending by ID
		return o2.marks - o1.marks;		// Descending by marks 
	}
	
}

class SorterDescendingMarks2 implements Comparator<Student>{
	
	@Override
	public int compare(Student o1, Student o2) {
		
		if(o1.marks == o2.marks)
			return o1.name.compareTo(o2.name); 		// Lexicographically Ascending by name
		return o2.marks - o1.marks;					// Descending by marks 
	}
	
}

class Student{
	
	String name;
	int id;
	int marks;
	
	Student(String name, int marks){
		this.name = name;
		this.marks = marks;
	}
	
	Student(int id, int marks){
		this.id = id;
		this.marks = marks;
	}
}


