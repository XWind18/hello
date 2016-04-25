package hello.test;

import hello.dao.MemberDao;
import hello.entity.Member;



public class Test {
	public static void main(String[] args) {
		System.out.println(Member.class);
		System.out.println(new MemberDao().query());
	}
}
