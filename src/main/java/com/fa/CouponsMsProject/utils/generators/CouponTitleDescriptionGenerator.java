package com.fa.CouponsMsProject.utils.generators;

import com.fa.CouponsMsProject.beans.Category;
import com.fa.CouponsMsProject.beans.Coupon;

import java.util.Random;

public class CouponTitleDescriptionGenerator {
	private static final Random randomNumber = new Random();

	public static void setTitleAndDesc(Coupon coupon, Category profile) {
		int randomNum = randomNumber.nextInt(4);
		switch (profile) {
			case GROCERY:
				switch (randomNum){
					case 0:
						coupon.setTitle("Shop like never before");
						coupon.setDescription("Only at our groceries you can shop just anything you desire");
						break;
					case 1:
						coupon.setTitle("We are open!!!");
						coupon.setDescription("New branch open at your location, come for opening discounts");
						break;
					case 2:
						coupon.setTitle("Everything is fresh!");
						coupon.setDescription("We always care for our products and our clients");
						break;
					case 3:
						coupon.setTitle("Food and more");
						coupon.setDescription("Our grocery is one of the biggest in the city, come with this coupon to get variety of discounts on all products");
						break;
				}
				break;
			case HOTELS:
				switch (randomNum){
					case 0:
						coupon.setTitle("Pamper your spouse!");
						coupon.setDescription("This coupon grants you 1+1 on all services our hotel provides");
						break;
					case 1:
						coupon.setTitle("Luxury for price of common");
						coupon.setDescription("We have set discount for all our luxury rooms, don't hesitate to order yours, number of rooms is limited");
						break;
					case 2:
						coupon.setTitle("Spa at our hotels for free!!!");
						coupon.setDescription("This coupon grants you free spa services at our hotels");
						break;
					case 3:
						coupon.setTitle("Just 4 u 2");
						coupon.setDescription("Variety of romantic rooms for you two to enjoy");
						break;
				}
				break;
			case BABY:
				switch (randomNum){
					case 0:
						coupon.setTitle("Don't wait till he cries");
						coupon.setDescription("Variety of infant creams at discount");
						break;
					case 1:
						coupon.setTitle("Big Baby Sale");
						coupon.setDescription("Variety of baby items at big discounts, order now with this coupon");
						break;
					case 2:
						coupon.setTitle("Discount on all toys");
						coupon.setDescription("We have a large variety of toys for babies. Order now to get discount");
						break;
					case 3:
						coupon.setTitle("Toddlers love it");
						coupon.setDescription("Our toys for toddlers are perfect for calming and relaxing them, granting parents quiet abd piece :)");
						break;
				}
				break;
			case RESTAURANT:
				switch (randomNum){
					case 0:
						coupon.setTitle("Don't Be hungry!");
						coupon.setDescription("Our restaurant invites you to try our specials and get discount");
						break;
					case 1:
						coupon.setTitle("Dinner for two");
						coupon.setDescription("Order a dinner for couples and get huge discount");
						break;
					case 2:
						coupon.setTitle("High kitchen celebration");
						coupon.setDescription("Our restaurant invites you to celebrate our anniversary with high kitchen dishes");
						break;
					case 3:
						coupon.setTitle("Taste Heaven");
						coupon.setDescription("Our 3 star Michelin restaurant invites you to try our best dishes and participate in tasting new ones");
						break;
				}
				break;
			case PERSONAL_CARE:
				switch (randomNum){
					case 0:
						coupon.setTitle("Face creams at discount");
						coupon.setDescription("Variety of face creams at discount only at our saloon");
						break;
					case 1:
						coupon.setTitle("Beauty starts here");
						coupon.setDescription("Variety of skin care services with small discounts");
						break;
					case 2:
						coupon.setTitle("Large personal care items sale");
						coupon.setDescription("Our saloon makes huge discounts on all our items this weekend, don't miss");
						break;
					case 3:
						coupon.setTitle("Beauty first");
						coupon.setDescription("People make first impression by look, so don't leave them a chance to make bad impression of you with our advanced beauty services");
						break;
				}
				break;
			case HOUSEHOLD:
				switch (randomNum){
					case 0:
						coupon.setTitle("Tools you need");
						coupon.setDescription("Our household shop holding a big sales event, just grab this coupon");
						break;
					case 1:
						coupon.setTitle("Variety of painting items at discount");
						coupon.setDescription("Don't miss opportunity to buy high quality wall paints and other painting items ");
						break;
					case 2:
						coupon.setTitle("Glue it");
						coupon.setDescription("When something is not glued good, you need more glue, and you can buy it with discount at our shop");
						break;
					case 3:
						coupon.setTitle("Hold your house!");
						coupon.setDescription("Variety of household items with various discounts at our shop");
						break;
				}
				break;
			case ELECTRONICS:
				switch (randomNum){
					case 0:
						coupon.setTitle("Variety of cellphones at discount");
						coupon.setDescription("Buy a cellphone for your taste with a discount");
						break;
					case 1:
						coupon.setTitle("Buy cellphone, get screen protector free");
						coupon.setDescription("Every cellphone you buy at our shop comes with high quality screen protector");
						break;
					case 2:
						coupon.setTitle("Cameras at discount");
						coupon.setDescription("Variety of popular video cameras at discount");
						break;
					case 3:
						coupon.setTitle("Electronics purchase wasn't so easier!!!");
						coupon.setDescription("Every order you place online at our shop, you get a good discount");
						break;
				}
				break;
			case TOOLS:
				switch (randomNum){
					case 0:
						coupon.setTitle("Variety of power-tools at discount");
						coupon.setDescription("Don't do it with bare hands, there are power tools for this, especially with a discount");
						break;
					case 1:
						coupon.setTitle("Chainsaws at discount");
						coupon.setDescription("Our shop offers huge selection of chainsaws, now with a discount");
						break;
					case 2:
						coupon.setTitle("MEGA TOOL SALE");
						coupon.setDescription("You heard it, MEGA SALE and MEGA DISCOUNTS");
						break;
					case 3:
						coupon.setTitle("Tool organizers at discount");
						coupon.setDescription("Variety of organizers and tool boxes at good discounts");
						break;
				}
				break;
			case VACATION:
				switch (randomNum){
					case 0:
						coupon.setTitle("בתן גב");
						coupon.setDescription("כן כן, בתן גב רק אצלנו");
						break;
					case 1:
						coupon.setTitle("Sunny beach");
						coupon.setDescription("Our company invites you to come to our best beach for caching some tan");
						break;
					case 2:
						coupon.setTitle("ITS REST TIME!!!");
						coupon.setDescription("Working hard is an excuse for resting like a king, and you can feel like a king at our resort, dot");
						break;
					case 3:
						coupon.setTitle("Holiday discount on various vacations");
						coupon.setDescription("Our company invites you to order a vacation at places we offer, and the discount on us");
						break;
				}
				break;
			case TRAVEL:
				switch (randomNum){
					case 0:
						coupon.setTitle("See the world with us!");
						coupon.setDescription("Order a trip to any distant location and we take care of everything else, yes the discount too");
						break;
					case 1:
						coupon.setTitle("Bar trip only with us");
						coupon.setDescription("We invite you to make a trip with us to europes best bars and clubs");
						break;
					case 2:
						coupon.setTitle("No Corona travels");
						coupon.setDescription("Our company attends a quality trip to south america with no masks attached, book your seat now and get a discount");
						break;
					case 3:
						coupon.setTitle("Around the world in 80 days");
						coupon.setDescription("See the world just for 80 days with our sea trip, book now");
						break;
				}
				break;
			case AUTOMOTIVE:
				switch (randomNum){
					case 0:
						coupon.setTitle("Polish Wax");
						coupon.setDescription("Quality polish and wax for your beloved car that saves a penny");
						break;
					case 1:
						coupon.setTitle("Got a scratch?! no worry");
						coupon.setDescription("We make quick scratch repairs for any car at fair prices, and for this week even with a discount");
						break;
					case 2:
						coupon.setTitle("Paint protection at discount");
						coupon.setDescription("we will install gloss and matt protection film for your car with great discount and in one day");
						break;
					case 3:
						coupon.setTitle("Make her event prettier!");
						coupon.setDescription("We offer various automotive body items, now with a discounts");
						break;
				}
				break;
			case FASHION:
				switch (randomNum){
					case 0:
						coupon.setTitle("Brands not for millionaires");
						coupon.setDescription("Popular clothes brands at available prices");
						break;
					case 1:
						coupon.setTitle("Class is not inherited, its constructed");
						coupon.setDescription("Variety of classic smokings and tuxedos at huge discounts, only for those who understand ;)");
						break;
					case 2:
						coupon.setTitle("Shop today, look great tomorrow");
						coupon.setDescription("Our shop invites you to participate in huge clothes sale from leading brands");
						break;
					case 3:
						coupon.setTitle("All eyes on me");
						coupon.setDescription("With our clothes selection all eyes will be on you, and even more with discounts");
						break;
				}
				break;
			case SOFTWARE:
				switch (randomNum){
					case 0:
						coupon.setTitle("Clean your pc");
						coupon.setDescription("Variety of pc leaning software with huge discounts");
						break;
					case 1:
						coupon.setTitle("Safety first");
						coupon.setDescription("Get windows defender antivirus just for 50% discount, not really");
						break;
					case 2:
						coupon.setTitle("Are your pc slow?");
						coupon.setDescription("Buy our pc tweak software for huge discount");
						break;
					case 3:
						coupon.setTitle("Make your Office work in the office");
						coupon.setDescription("MS Office subscription for free for year");
						break;
				}
				break;
		}
	}
}
