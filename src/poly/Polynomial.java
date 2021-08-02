package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 *
 * @author runb-cs112
 *
 *
 * Student Name: Vighnesh Hemnani
 *
 *
 *
 */
public class Polynomial {

	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3
	 * </pre>
	 *
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc)
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}

	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 *
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */

		public static Node add(Node poly1, Node poly2) {


			Node pointer1 = poly1;
			Node pointer2 = poly2;
			Node first = null;
			Node last = null;


			while(pointer1 != null && pointer2 != null) {

				float sumofcoeff = 0;

				if(pointer1.term.degree > pointer2.term.degree){

					Node newNode = new Node(pointer2.term.coeff, pointer2.term.degree, null);

					if(last != null){
						last.next=newNode;
					}
					else{
						first=newNode;
					}

					last=newNode;
					pointer2=pointer2.next;
				}
				else if(pointer1.term.degree < pointer2.term.degree){

						Node newNode= new Node(pointer1.term.coeff, pointer1.term.degree, null);
						if(last != null){
							last.next=newNode;
						}
						else{
							first=newNode;
						}

						last=newNode;
						pointer1=pointer1.next;

					}

					else if(pointer1.term.degree == pointer2.term.degree){

						sumofcoeff = pointer1.term.coeff + pointer2.term.coeff;

						if (sumofcoeff == 0) {
							pointer1=pointer1.next;
							pointer2=pointer2.next;
							continue;
						}

						Node newNode= new Node(sumofcoeff, pointer1.term.degree, null);

						if(last != null){
							last.next=newNode;
						}
						else{
							first=newNode;
						}

							last=newNode;
							pointer1=pointer1.next;
							pointer2=pointer2.next;
						}
			}


			while(pointer1 == null && pointer2 != null){
				Node newNode= new Node(pointer2.term.coeff, pointer2.term.degree, null);

				if(last != null){
					last.next=newNode;
				}

				else{
					first=newNode;
				}

				last=newNode;
				pointer2=pointer2.next;
			}


			while(pointer1 != null && pointer2 == null){

				Node newNode= new Node(pointer1.term.coeff, pointer1.term.degree, null);

				if(last != null){
					last.next=newNode;
				}
				else{
					first=newNode;
				}
				last=newNode;
				pointer1=pointer1.next;
			}

			return first;
		}


	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 *
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {

		Node intialLLfront=null, initialLLtail= null;

		for(Node pointer1 = poly1 ; pointer1 != null ; pointer1 = pointer1.next){

			for(Node pointer2= poly2; pointer2 != null; pointer2= pointer2.next){

				float product = pointer1.term.coeff * pointer2.term.coeff;
				int degree =  pointer1.term.degree + pointer2.term.degree;

				Node newNode= new Node(product, degree, null);

				if(initialLLtail != null){
					initialLLtail.next=newNode;
				}

				else{
					intialLLfront=newNode;
				}
				initialLLtail=newNode;
			}
		}

		Node finalfront = null;
		Node finallast = null;

		if (intialLLfront == null || initialLLtail == null) {
			return null;
		}

		int highestOrder= initialLLtail.term.degree;

		for(int currdeg = 0; currdeg <= highestOrder; currdeg++){

			float sumofcoeff = 0;

			for(Node ptr= intialLLfront; ptr!= null; ptr=ptr.next){

				if(ptr.term.degree == currdeg){
					sumofcoeff = sumofcoeff + ptr.term.coeff;
				}
			}

			if (sumofcoeff == 0) continue;

			Node tempNode = new Node(sumofcoeff, currdeg, null);

			if(finallast != null){
				finallast.next = tempNode;
			}
			else {
				finalfront = tempNode;
			}
			finallast = tempNode;

		}

		return finalfront;

	}

	/**
	 * Evaluates a polynomial at a given value.
	 *
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {

		float answer = 0;
		Node front = poly;

		while (front != null) {
			double currentterm = Math.pow(x,front.term.degree) * front.term.coeff;
			answer = (float) (answer + currentterm);
			front = front.next;
		}

		return answer;

	}

	/**
	 * Returns string representation of a polynomial
	 *
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		}

		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}
}
