package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import photomanager.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {

	// expected: earthwindfire, 400, 400, 09/21/1978-21:00
	@Test
	public void studentPhotoConstructor() {
		Photo pic1 = new Photo("earthwindfire", 400, 400, "09/21/1978-21:00");
		System.out.println("test1: " + pic1);
	}

	// expected: earthwindfire, 400, 400, 09/21/1978-21:00
	@Test
	public void studentPhotoCopy() {
		Photo pic1 = new Photo("earthwindfire", 400, 400, "09/21/1978-21:00");
		Photo pic2 = new Photo(pic1);
		System.out.println("test2: " + pic2);
	}

	// expected: earthwindfire, 400, 400, 09/21/1978-21:00
	@Test
	public void studentPhototoString() {
		Photo pic1 = new Photo("earthwindfire", 400, 400, "09/21/1978-21:00");
		String printPic = pic1.toString();
		System.out.println("test3: " + printPic);
	}

	// expected: arianagrande/34/35/08/15/2020-06:09
	@Test
	public void studentPhotoGetters() {
		Photo pic1 = new Photo("arianagrande", 34, 35, "08/15/2020-06:09");
		String printGetters = "";
		printGetters += pic1.getPhotoSource() + "/";
		printGetters += pic1.getWidth() + "/";
		printGetters += pic1.getHeight() + "/";
		printGetters += pic1.getDate();

		System.out.println("test4: " + printGetters);
	}

	// expected: can you stay up all night, the gays love ariana grande and i
	// love them
	@Test
	public void studentPhotoComments() {
		Photo pic1 = new Photo("arianagrande", 34, 35, "08/15/2020-06:09");
		pic1.addComments("can you stay up all night");
		pic1.addComments("the gays love ariana grande and i love them");
		System.out.println("test5: " + pic1.getComments());
	}

	// expected: -637787168
	@Test
	public void studentPhotoscompareTo() {
		Photo pic1 = new Photo("arianagrande", 34, 35, "08/15/2020-06:09");
		Photo pic2 = new Photo("earthwindfire", 400, 400, "09/21/1978-21:00");
		System.out.println("test6: " + pic1.compareTo(pic2));
	}

	// expected: addPhoto: Invalid arguments
	// arianagrande,34,35,08/15/2020-06:09
	// earthwindfire,400,400,09/21/1978-21:00
	// mitski,1,520,05/31/2014-19:00
	// mikimatsubara,80,80,11/05/1979-14:00
	@Test
	public void studentPhotoManager() {
		PhotoManager allPhotos = new PhotoManager();
		allPhotos.addPhoto("arianagrande", 34, 35, "08/15/2020-06:09");
		allPhotos.addPhoto("earthwindfire", 400, 400, "09/21/1978-21:00");
		allPhotos.addPhoto("mitski", 01, 520, "05/31/2014-19:00");
		allPhotos.addPhoto("mikimatsubara", 80, 80, "11/05/1979-14:00");
		try {
			allPhotos.addPhoto("beabadoobee", 01, 01, null);
		} catch (IllegalArgumentException e) {
		}

		System.out.println("test7: " + allPhotos.toString());
	}

	// expected: 3/-1
	@Test
	public void studentPhotoManagerFindPhoto() {
		PhotoManager allPhotos = new PhotoManager();
		allPhotos.addPhoto("arianagrande", 34, 35, "08/15/2020-06:09");
		allPhotos.addPhoto("earthwindfire", 400, 400, "09/21/1978-21:00");
		allPhotos.addPhoto("mitski", 01, 520, "05/31/2014-19:00");
		allPhotos.addPhoto("mikimatsubara", 80, 80, "11/05/1979-14:00");

		int pic1 = allPhotos.findPhoto("mikimatsubara");
		int pic2 = allPhotos.findPhoto("rinasawayama");
		System.out.println("test8: " + pic1 + "/" + pic2);
	}

	// expected: please hurry leave me/stay with me
	@Test
	public void studentPhotoManagerAddComment() {
		PhotoManager allPhotos = new PhotoManager();
		allPhotos.addPhoto("arianagrande", 34, 35, "08/15/2020-06:09");
		allPhotos.addPhoto("earthwindfire", 400, 400, "09/21/1978-21:00");
		allPhotos.addPhoto("mitski", 01, 520, "05/31/2014-19:00");
		allPhotos.addPhoto("mikimatsubara", 80, 80, "11/05/1979-14:00");

		allPhotos.addComment("mitski", "please hurry leave me");
		allPhotos.addComment("mikimatsubara", "stay with me");

		System.out.println("test9: " + allPhotos.getComments("mitski") + "/" + 
							allPhotos.getComments("mikimatsubara"));
	}

	// expected:
	// mitski,1,520,05/31/2014-19:00
	// mikimatsubara,80,80,11/05/1979-14:00
	@Test
	public void studentPhotoManagerClear() {
		PhotoManager allPhotos = new PhotoManager();
		allPhotos.addPhoto("arianagrande", 34, 35, "08/15/2020-06:09");
		allPhotos.addPhoto("earthwindfire", 400, 400, "09/21/1978-21:00");

		allPhotos.removeAllPhotos();

		allPhotos.addPhoto("mitski", 01, 520, "05/31/2014-19:00");
		allPhotos.addPhoto("mikimatsubara", 80, 80, "11/05/1979-14:00");

		System.out.println("test10: " + allPhotos.toString());
	}

	// expected:
	// earthwindfire,400,400,09/21/1978-21:00
	// mitski,1,520,05/31/2014-19:00
	// mikimatsubara,80,80,11/05/1979-14:00

	@Test
	public void studentPhotoManagerRemove() {
		PhotoManager allPhotos = new PhotoManager();
		allPhotos.addPhoto("arianagrande", 34, 35, "08/15/2020-06:09");
		allPhotos.addPhoto("earthwindfire", 400, 400, "09/21/1978-21:00");
		allPhotos.addPhoto("mitski", 01, 520, "05/31/2014-19:00");
		allPhotos.addPhoto("mikimatsubara", 80, 80, "11/05/1979-14:00");

		allPhotos.removePhoto("arianagrande");

		System.out.println("test11: " + allPhotos);
	}

	// expected:
	// arianagrande,34,35,08/15/2020-06:09
	// earthwindfire,400,400,09/21/1978-21:00
	// mitski,1,520,05/31/2014-19:00
	// mikimatsubara,80,80,11/05/1979-14:00
	@Test
	public void studentPhotoManagerSort() {
		PhotoManager allPhotos = new PhotoManager();
		allPhotos.addPhoto("arianagrande", 34, 35, "08/15/2020-06:09");
		allPhotos.addPhoto("mikimatsubara", 80, 80, "11/05/1979-14:00");
		allPhotos.addPhoto("earthwindfire", 400, 400, "09/21/1978-21:00");
		allPhotos.addPhoto("mitski", 01, 520, "05/31/2014-19:00");

		allPhotos.sortPhotosByDate();
		System.out.println("test12: " + allPhotos);
	}

	// expected:
	// umcp/college1.jpg,300,400,10/18/2020-17:10
	// umcp/college8.jpg,200,200,10/20/2020-18:10
	// umcp/college3.jpg,300,400,10/11/2020-17:10
	// umcp/college4.jpg,200,200,09/18/2020-18:10
	// umcp/college7.jpg,300,400,10/18/2020-17:10
	@Test
	public void studentPhotoManagerLoad() {
		PhotoManager allPhotos = new PhotoManager();
		allPhotos.loadPhotos("photoInfoToLoad.txt");

		System.out.println("test13: " + allPhotos);
	}
}