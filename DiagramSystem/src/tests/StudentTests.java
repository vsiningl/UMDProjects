package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import system.TwoDimArrayUtil;

public class StudentTests {

	@Test
	public void test01() {
		char[][] arr = {{'r','e','d'}, {'c','a','t'},{'p','o','p'}};
		System.out.println(Arrays.deepToString(arr));
		TwoDimArrayUtil.rotateTopOneRow(arr);
		System.out.println(Arrays.deepToString(arr));
	}
	
	@Test
	public void test02() {
		char[][] arr = {{'r','e','d'}, {'c','a','t'},{'p','o','p'}};
		System.out.println(Arrays.deepToString(arr));
		TwoDimArrayUtil.rotateLeftOneColumn(arr);
		System.out.println(Arrays.deepToString(arr));
	}
	
	@Test
	public void test03() {
		char[][] arr = {{'r', 'e', 'd'}, {'c','a','t'},{'p','o','p'}};
		char[][] rra = {{'1', '2','3'},{'4','5','6'},{'7','8','9'}};
		char[][] array = TwoDimArrayUtil.appendTopBottom(arr, rra);
		System.out.println(Arrays.deepToString(array));
	}
	
	@Test
	public void test04() {
		char[][] arr = {{'r', 'e', 'd'}, {'c','a','t'},{'p','o','p'}};
		char[][] rra = {{'1', '2','3'},{'4','5','6'}, {'7','8','9'}};
		char[][] array = TwoDimArrayUtil.appendLeftRight(arr, rra);
		System.out.println(Arrays.deepToString(array));
	}

}
