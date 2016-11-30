package sortcomparison;

import java.util.*;

public class BasicSorter implements Sorter {

	private static final Random random = new Random();


	/**
	 * Sorts part or all of a list in ascending order.
	 * 
	 * @param data
	 *            The list of elements to sort
	 * @param firstIndex
	 *            Index of the first element to be sorted.
	 * @param numberToSort
	 *            The number of elements in the section to be sorted.
	 */
	public void insertionSort(ArrayList<String> data, int firstIndex, int numberToSort) {
		/*
		 * (1) For each unsorted element: (a) For each sorted element, starting at the right-end of the sorted section
		 * (i) Compare the unsorted element with the sorted element. (ii)If the unsorted element is less than the sorted
		 * element, then move the sorted element one to the right. Else, put the unsorted element in the empty slot and
		 * break (a) loop.
		 */
		String unsortedWord = null;
		String sortedWord = null;

		List<String> sublist = data.subList(firstIndex, firstIndex + numberToSort);

		int firstUnsorted = 0;

		while (numberToSort > 0) {
			unsortedWord = sublist.get(firstUnsorted);
			int empty;
			for (empty = firstUnsorted; empty > 0; empty--) {

				sortedWord = sublist.get(empty - 1);
				if ((unsortedWord.compareTo(sortedWord) < 0)) {
					// if the first unsorted word is before the sorted word,
					// move the sorted word over one place to the empty slot.
					sublist.set(empty, sortedWord);
					continue;
				} else {
					// if the unsorted word is after the ith word,
					// place the unsorted word into the empty slot.
					break;
				}
			}
			sublist.set(empty, unsortedWord);

			firstUnsorted++;
			numberToSort--;
		}
	}


	/**
	 * Sorts part or all of the list, data, in ascending order. quickSort() must: - Call the insertionSort() function in
	 * this interface for segments of size 15 or less. - Use the median-of-three method to prevent O(n^2) running time
	 * for sorted data sets - Call the partition() function in this interface to do its partitioning.
	 * 
	 * @param data
	 *            The list of elements to sort
	 * @param firstIndex
	 *            Index of the first element to be sorted.
	 * @param numberToSort
	 *            The number of elements in the section to be sorted.
	 */
	public void quickSort(ArrayList<String> data, int firstIndex, int numberToSort) {
		/*
		 * (1) Partition the Array (2) Compare the sizes of the left and right (3) Quick Sort the left side. (4) Quick
		 * sort the right side.
		 * 
		 */


		if (numberToSort < 16) {
			insertionSort(data, firstIndex, numberToSort);
			return;
		}

		int pivot = partition(data, firstIndex, numberToSort);
		int lengthFirstHalf = pivot - firstIndex;
		int lengthSecondHalf = numberToSort - lengthFirstHalf;

		// System.out.printf("first index: %6d, length 1st half: %6d, pivot: %6d, length 2nd half: %6d\n", firstIndex,
		// lengthFirstHalf, pivot, lengthSecondHalf);

		quickSort(data, firstIndex, pivot - firstIndex);
		quickSort(data, pivot, lengthSecondHalf);
	}


	/**
	 * Partitions part (or all) of the list. The range of indices included in the partitioning is [firstIndex,
	 * firstIndex + numberToPartition -1].
	 * 
	 * @param data
	 * @param firstIndex
	 * @param numberToPartition
	 * @return The index, relative to data[0], where the pivot value is located at the end of this partitioning.
	 */
	public int partition(final ArrayList<String> data, final int firstIndex, final int numberToPartition) {

		int a = random.nextInt(numberToPartition) + firstIndex;
		// int b = random.nextInt(numberToPartition) + firstIndex;
		// int c = random.nextInt(numberToPartition) + firstIndex;

		int pivot = a; // medianIndex(data, a, b, c);

		Collections.swap(data, pivot, firstIndex);
		pivot = firstIndex;
		int low = pivot + 1;
		int high = firstIndex + numberToPartition - 1;

		while (low < high) {

			if (data.get(low).compareTo(data.get(pivot)) <= 0) {
				// if low < pivot
				// increase low index.
				low++;
			} else {
				if (data.get(high).compareTo(data.get(pivot)) <= 0) {
					// if low > pivot and high < pivot
					// switch high and low.
					Collections.swap(data, low, high);
				}
				// decrease high index.
				// if low > pivot and high > pivot
				// decrease high index

				high--;
			}
			// printList(data);
		}
		pivot = high;
		Collections.swap(data, firstIndex, pivot);
		return pivot;
	}


	// TODO
	private int medianIndex(ArrayList<String> data, int a, int b, int c) {
		// TODO Auto-generated method stub
		return 0;

	}


	int largestMergeLength = 0;


	/**
	 * Sorts part or all of a list in ascending order.
	 * 
	 * mergeSort() must: - Call the insertionSort() function in this interface for segments of size 15 or less. - Call
	 * the merge() function in this interface to do its merging.
	 * 
	 * @param data
	 *            list of elements to sort
	 * @param firstIndex
	 *            Index of the first element to be sorted.
	 * @param numberToSort
	 *            The number of elements in the section to be sorted.
	 */
	public void mergeSort(final ArrayList<String> data, int firstIndex, int numberToSort) {
		// System.out.printf("\n\nFull, before\t[%d - %d]:\t", firstIndex, firstIndex + numberToSort - 1);
		// printList(data.subList(firstIndex, firstIndex + numberToSort));

		if (numberToSort < 16) {
			insertionSort(data, firstIndex, numberToSort);
			return;
		}

		int leftStart = firstIndex;
		int leftLength = numberToSort / 2;
		int rightStart = leftStart + leftLength;
		int rightLength = numberToSort - leftLength;


		// System.out.printf("Left, before\t[%d - %d]:\t", leftStart, leftStart + leftLength - 1);
		// printList(data.subList(leftStart, leftStart + leftLength));

		mergeSort(data, leftStart, leftLength);
		// System.out.printf("Left, after\t[%d - %d]:\t", leftStart, leftStart + leftLength - 1);
		// printList(data.subList(leftStart, leftStart + leftLength));

		// System.out.printf("Right, before\t[%d - %d]:\t", rightStart, rightStart + rightLength - 1);
		// printList(data.subList(rightStart, rightStart + rightLength));
		mergeSort(data, rightStart, rightLength);
		// System.out.printf("Right, after\t[%d - %d]:\t", rightStart, rightStart + rightLength - 1);
		// printList(data.subList(rightStart, rightStart + rightLength));

		// System.out.printf("Merged, before\t[%d - %d]:\t", firstIndex, firstIndex + numberToSort - 1);
		// List<String> merged = data.subList(firstIndex, firstIndex + numberToSort);
		// printList(merged);

		merge(data, leftStart, leftLength, rightLength);

		System.out.printf("Merged, after\t[%d - %d]:\t", firstIndex, firstIndex + numberToSort - 1);
		printList(data.subList(leftStart, leftStart + numberToSort));

		// if (numberToSort > largestMergeLength) {
		// largestMergeLength = numberToSort;
		// System.out.printf("Merged length: %6d\n", largestMergeLength);
		// }

		if (numberToSort == data.size()) {
			copy = null;
		}
	}


	ArrayList<String> copy;


	/**
	 * Merges two sorted segments into a single sorted segment. The left and right segments are contiguous.
	 * 
	 * @param data
	 *            The list of elements to merge
	 * @param firstIndex
	 *            Index of the first element of the left segment.
	 * @param leftSegmentSize
	 *            The number of elements in the left segment.
	 * @param rightSegmentSize
	 *            The number of elements in the right segment.
	 */
	public void merge(final ArrayList<String> data, int firstIndex, int leftSegmentSize, int rightSegmentSize) {
		if (copy == null) {
			copy = (ArrayList<String>) data.clone();
		} else if (copy.size() != data.size()) {
			throw new RuntimeException("Programming error. Copy should have been erased at the end of the last sort.");
		}

		int leftIndex = firstIndex;
		int rightIndex = firstIndex + leftSegmentSize;

		List<String> left = copy.subList(leftIndex, leftIndex + leftSegmentSize);
		List<String> right = copy.subList(rightIndex, rightIndex + rightSegmentSize);

		int dataIndex = leftIndex;

		leftIndex = 0;
		rightIndex = 0;

		String element;
		while (leftIndex < left.size() || rightIndex < right.size()) {
			if (rightIndex == right.size()
					|| (leftIndex < left.size() && left.get(leftIndex).compareTo(right.get(rightIndex)) < 0)) {
				element = left.get(leftIndex);
				leftIndex++;
			} else {
				element = right.get(rightIndex);
				rightIndex++;
			}

			data.set(dataIndex, element);
			dataIndex++;
		}

		int lastIndex = firstIndex + leftSegmentSize + rightSegmentSize;

		for (int i = firstIndex; i < lastIndex; i++) {
			copy.set(i, data.get(i));
		}

	}


	/**
	 * EXTRA CREDIT
	 * 
	 * Sorts the list in ascending order.
	 * 
	 * @param data
	 *            The list of elements to sort
	 */
	public void heapSort(ArrayList<String> data) {
		throw new RuntimeException("Not implemented");
	}


	/**
	 * EXTRA CREDIT
	 * 
	 * Heapifies the given list.
	 * 
	 * @param data
	 *            The list to heapify.
	 */
	public void heapify(ArrayList<String> data) {
		throw new RuntimeException("Not implemented");
	}


	public static void main(String[] args) {
		BasicSorter sorter = new BasicSorter();

		ArrayList<String> list = new ArrayList<>();
		String[] b = new String[32];

		for (int i = 0; i < b.length; i++) {
			b[i] = String.valueOf((char) (random.nextInt(26) + 65));
		}

		// String[] b = { "S", "D", "G", "C" };


		Collections.addAll(list, b);

		int firstIndex = 0;
		sorter.mergeSort(list, firstIndex, list.size() - firstIndex);

		printList(list);
	}


	private static void printList(List<String> list) {
		for (String s : list) {
			System.out.print(s + "\t");
		}
		System.out.println();
	}
}