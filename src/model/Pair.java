package model;

public class Pair<A extends Comparable<A>,B extends Comparable<B>> implements Comparable<Pair<A,B>>{
		private A p1;
		private B p2;
		private char comparison;
		public static final char COMPARE_BY_FIRST='f';
		public static final char COMPARE_BY_SECOND='s';
		public static final char COMPARE_BY_BOTH='b';
		public Pair(A p1, B p2, char comparison) {
			this.p1 = p1;
			this.p2 = p2;
			this.comparison = comparison;
		}
		public A getP1() {
			return p1;
		}
		public void setP1(A p1) {
			this.p1 = p1;
		}
		public B getP2() {
			return p2;
		}
		public void setP2(B p2) {
			this.p2 = p2;
		}
		public boolean equals(Pair<A,B> pair) {
			return p1.compareTo(pair.p1) == 0 && p2.compareTo(pair.p2) == 0? true:false;
		}
		@Override
		public int compareTo(Pair<A, B> o) {
			// TODO Auto-generated method stub
			if(comparison==COMPARE_BY_BOTH) {
				return p1.compareTo(o.p1) == 0 && p2.compareTo(o.p2)==0?0:1;
			} else if(comparison==COMPARE_BY_FIRST) {
				return p1.compareTo(o.p1);
			} else {
				return p2.compareTo(o.p2);
			}
			
		}
		@Override
		public String toString() {
			return p1.toString() + ":" +p2.toString() + "";
		}
	}