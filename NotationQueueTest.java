package cmsc204Assign2;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NotationQueueTest {
	public NotationQueue<String> stringQ;
	public String a = "a", b = "b", c = "c", d = "d", e = "e", f = "f";
	public ArrayList<String> fill = new ArrayList<String>();

	// STUDENT: student tests will use the doubleQ
	public NotationQueue<Double> doubleQ;
	// STUDENT: add variables as needed for your student tests
	Double first = new Double(1.0);
	Double second = new Double(2.0);
	Double third = new Double(3.0);

	@Before
	public void setUp() throws Exception {
		stringQ = new NotationQueue<String>(5);
		stringQ.enqueue(a);
		stringQ.enqueue(b);
		stringQ.enqueue(c);

		// STUDENT: add setup for doubleQ for student tests
		
		doubleQ = new NotationQueue<Double>(6);
		doubleQ.enqueue(first);
		doubleQ.enqueue(second);
		doubleQ.enqueue(third);
	}

	@After
	public void tearDown() throws Exception {
		stringQ = null;
		doubleQ = null;
	}

	@Test
	public void testIsEmpty() throws QueueUnderflowException {
		assertEquals(false, stringQ.isEmpty());
		stringQ.dequeue();
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(true, stringQ.isEmpty());
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(a, stringQ.dequeue());
			assertEquals(b, stringQ.dequeue());
			assertEquals(c, stringQ.dequeue());
			// Queue is empty, next statement should cause QueueUnderFlowException
			stringQ.dequeue();
			assertTrue("This should have caused an QueueUnderflowException", false);
		} catch (QueueUnderflowException e) {
			assertTrue("This should have caused an QueueUnderflowException", true);
		} catch (Exception e) {
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
	}

	@Test
	public void testDequeueStudent() {
		try {
			assertEquals(first, doubleQ.dequeue());
			assertEquals(second, doubleQ.dequeue());
			assertEquals(third, doubleQ.dequeue());
			// Queue is empty, next statement should cause QueueUnderFlowException
			doubleQ.dequeue();
			assertTrue("This should have caused an QueueUnderflowException", false);
		} catch (QueueUnderflowException e) {
			assertTrue("This should have caused an QueueUnderflowException", true);
		} catch (Exception e) {
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
	}

	@Test
	public void testSize() throws QueueUnderflowException, QueueOverflowException {
		assertEquals(3, stringQ.size());
		stringQ.enqueue(d);
		assertEquals(4, stringQ.size());
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(2, stringQ.size());
	}

	@Test
	public void testEnqueue() {
		try {
			assertEquals(3, stringQ.size());
			assertEquals(true, stringQ.enqueue(d));
			assertEquals(4, stringQ.size());
			assertEquals(true, stringQ.enqueue(e));
			assertEquals(5, stringQ.size());
			// Queue is full, next statement should cause QueueOverFlowException
			stringQ.enqueue(f);
			assertTrue("This should have caused an QueueOverflowException", false);
		} catch (QueueOverflowException e) {
			assertTrue("This should have caused an QueueOverflowException", true);
		} catch (Exception e) {
			assertTrue("This should have caused an QueueOverflowException", false);
		}
	}

	@Test
	public void testEnqueueStudent() {
		try {
			assertEquals(3, doubleQ.size());
			assertEquals(true, doubleQ.enqueue(first));
			assertEquals(4, doubleQ.size());
			assertEquals(true, doubleQ.enqueue(second));
			assertEquals(5, doubleQ.size());
			doubleQ.enqueue(third);
			assertEquals(6, doubleQ.size());
			doubleQ.enqueue(first);
			assertTrue("This should have caused an QueueOverflowException", false);
		} catch (QueueOverflowException e) {
			assertTrue("This should have caused an QueueOverflowException", true);
		} catch (Exception e) {
			assertTrue("This should have caused an QueueOverflowException", false);
		}
	}

	@Test
	public void testIsFull() throws QueueOverflowException {
		assertEquals(false, stringQ.isFull());
		stringQ.enqueue(d);
		stringQ.enqueue(e);
		assertEquals(true, stringQ.isFull());
	}

	@Test
	public void testToString() throws QueueOverflowException {
		assertEquals("abc", stringQ.toString());
		stringQ.enqueue(d);
		assertEquals("abcd", stringQ.toString());
		stringQ.enqueue(e);
		assertEquals("abcde", stringQ.toString());
	}

	@Test
	public void testToStringStudent() throws QueueOverflowException {
		// Use the doubleQ for student tests
		assertEquals("1.02.03.0", doubleQ.toString());
		doubleQ.enqueue(first);
		assertEquals("1.02.03.01.0", doubleQ.toString());
		doubleQ.enqueue(second);
		assertEquals("1.02.03.01.02.0", doubleQ.toString());
	}

	@Test
	public void testToStringDelimiter() throws QueueOverflowException {
		assertEquals("a%b%c", stringQ.toString("%"));
		stringQ.enqueue(d);
		assertEquals("a&b&c&d", stringQ.toString("&"));
		stringQ.enqueue(e);
		assertEquals("a/b/c/d/e", stringQ.toString("/"));
	}

	@Test
	public void testFill() throws QueueUnderflowException {
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		// start with an empty queue
		stringQ = new NotationQueue<String>(5);
		// fill with an ArrayList
		stringQ.fill(fill);
		assertEquals(3, stringQ.size());
		assertEquals("apple", stringQ.dequeue());
		assertEquals("banana", stringQ.dequeue());
		assertEquals("carrot", stringQ.dequeue());
	}

}