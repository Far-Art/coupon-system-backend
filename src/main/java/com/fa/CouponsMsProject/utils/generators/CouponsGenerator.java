package com.fa.CouponsMsProject.utils.generators;

import com.fa.CouponsMsProject.beans.Category;
import com.fa.CouponsMsProject.beans.Coupon;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Random;

@Component
public class CouponsGenerator {


    public Coupon getCoupon(Category profile) {
        return builder(profile);
    }

    private Coupon builder(Category profile) {
        Coupon coupon = Coupon.builder()
                .amount(CouponAmountGenerator.getAmount())
                .category(profile)
                .price(CouponPriceGenerator.getPrice())
                .startDate((Date) CouponDateGenerator.getDate(1, 0, 0, true, CouponDateGenerator.DateType.SQL))
                .endDate((Date) CouponDateGenerator.getDate(10, 0, 4, true, CouponDateGenerator.DateType.SQL))
                .image(getImage(profile))
                .build();
        CouponTitleDescriptionGenerator.setTitleAndDesc(coupon, profile);
        return coupon;
    }

    private String getImage(Category profile) {
        switch (profile) {
            case HOTELS:
                return hotelsImages();
            case RESTAURANT:
                return restaurantImages();
            case PERSONAL_CARE:
                return personalCareImages();
            case ELECTRONICS:
                return electronicsImages();
            case TOOLS:
                return toolsImages();
            case AUTOMOTIVE:
                return automotiveImages();
            case FASHION:
                return fashionImages();
            default:
                return "assets/images/empty_coupon_img.png";
        }
    }

    private String hotelsImages() {
        Random random = new Random();
        switch (random.nextInt() % 5) {
            case 0:
                return "https://cdn1.parksmedia.wdprapps.disney.com/resize/mwImage/1/900/450/75/dam/wdpro-assets/dlr/places-to-stay/disneyland-hotel/resort-overview/disneyland-hotel-06.jpg?1717607880260";
            case 1:
                return "https://assets.hrewards.com/assets/SHR_Hamburg_rooms_Standard_King_1_d5356d0cd0.jpg";
            case 2:
                return "https://www.savills.co.uk/_images/adobestock-539646437.jpg";
            case 3:
                return "https://www.hilton.com/im/en/CZMPCHH/7888781/czmpc-pool-3.jpg?impolicy=crop&cw=5000&ch=3203&gravity=NorthWest&xposition=0&yposition=64&rw=1280&rh=820";
            default:
                return "https://nitu.mx/wp-content/uploads/2019/01/Hilton-Playa-del-Carmen.jpg";
        }
    }

    private String restaurantImages() {
        Random random = new Random();
        switch (random.nextInt() % 5) {
            case 0:
                return "https://images.pexels.com/photos/262978/pexels-photo-262978.jpeg";
            case 1:
                return "https://toohotel.com/wp-content/uploads/2022/09/TOO_restaurant_Panoramique_vue_Paris_nuit_v2-scaled.jpg";
            case 2:
                return "https://restaurantkronborg.dk/wp-content/uploads/2023/09/Restaurant-Kronborg-Billeder-58-1024x683.jpg";
            case 3:
                return "https://b.zmtcdn.com/data/collections/684397cd092de6a98862220e8cc40aca_1709810183.png";
            default:
                return "https://assets.bonappetit.com/photos/61ba70da510874520d257b78/16:9/w_1599,h_899,c_limit/LEDE_Oma's%20Hideaway,%20Credit%20Christine%20Dong.jpg";
        }
    }

    private String personalCareImages() {
        Random random = new Random();
        switch (random.nextInt() % 5) {
            case 0:
                return "https://cdn.shopify.com/s/files/1/0070/7032/files/how-to-start-a-skincare-line-glow-oasis.jpg?v=1666895341";
            case 1:
                return "https://images.everydayhealth.com/images/skin-beauty/what-are-natural-skin-care-products-alt-1440x810.jpg?sfvrsn=616dd3f2_1";
            case 2:
                return "https://www.aak.com/contentassets/2222a08faf95461b88bc582bd46eadec/aak-personal-care-sustainable-oil-fat-solutions-for-beauty-16x9-1500.jpg";
            case 3:
                return "https://media.istockphoto.com/id/584574708/photo/soap-bar-and-liquid-shampoo-shower-gel-towels-spa-kit.jpg?s=612x612&w=0&k=20&c=TFeQmTwVUwKY0NDKFFORe3cwDCxRtotFgEujMswn3dc=";
            default:
                return "https://cdn.shopaccino.com/refresh/articles/personal-care-827897_l.jpg?v=422";
        }
    }

    private String electronicsImages() {
        Random random = new Random();
        switch (random.nextInt() % 5) {
            case 0:
                return "https://m.media-amazon.com/images/I/717tC4Mk9SL._AC_SL1000_.jpg";
            case 1:
                return "https://d2csxpduxe849s.cloudfront.net/media/E32629C6-9347-4F84-81FEAEF7BFA342B3/C0DBF8AC-DB0E-4621-96C05D3B6E366D02/E142C7C6-62EF-4320-A6C2DB6EB2B3F4E1/WebsiteJpg_XL-FELEC_Main%20Visual_Purple_Website.jpg";
            case 2:
                return "https://www.schott.com/-/media/project/onex/shared/teasers/consumer-electronics/consumer-electronics_01-displays_00_720x450.jpg?rev=fe6df7a508644a7d8e294e6c294a317b";
            case 3:
                return "https://www.compliancegate.com/wp-content/uploads/2020/07/electronic-products-regulations-singapore.jpg";
            default:
                return "https://globalnaps.org/wp-content/uploads/2017/10/ICT-electronics.jpg";
        }
    }

    private String toolsImages() {
        Random random = new Random();
        switch (random.nextInt() % 5) {
            case 0:
                return "https://www.rakenapp.com/img/asset/YXNzZXRzL2hhbW1lci10b29scy5qcGc=?w=1024&h=682.66666666667&fit=crop&q=85&s=174d6de3497c587bdc76dd2bf7cb1eed";
            case 1:
                return "https://www.parkerbrent.com.au/wp-content/uploads/2023/07/keys-workshop-mechanic-tools-162553.jpeg";
            case 2:
                return "https://grainger-prod.adobecqms.net/content/dam/grainger/gus/en/public/digital-tactics/know-how/hero/SS-KH_8-power-tool-maintenance-tips_KH-HRO.jpg";
            case 3:
                return "https://www.safework.nsw.gov.au/__data/assets/image/0020/422084/powertools.jpg";
            default:
                return "https://dam.thdstatic.com/content/production/uK2_C5h1xwhfsWTETDNMPg/7N573ccuIYyC59JUudoz6w/optimizedFile/2023_HC_Mechanic_Tool_Sets.jpg?im=Resize=(920,575)";
        }
    }

    private String automotiveImages() {
        Random random = new Random();
        switch (random.nextInt() % 5) {
            case 0:
                return "https://carfromjapan.com/wp-content/uploads/2019/03/old-car.jpg";
            case 1:
                return "https://images.shiksha.com/mediadata/images/articles/1568628637phpM6zioi.jpeg";
            case 2:
                return "https://res.cloudinary.com/dsxfn6o4q/image/upload/c_fill,g_center,h_510,w_659/v1669116025/gfqrxes5yhdvnmhjvcgl.jpg";
            case 3:
                return "https://www.imarcgroup.com/blogs/files/70bdf6f6-53f3-41be-b69f-3f23e6d1e2f8auto-parts.webp";
            default:
                return "https://images.autods.com/OfficialSite/New/20231009123226/20-Best-Selling-Auto-Parts-To-Start-Dropshipping-Today.png";
        }
    }

    private String fashionImages() {
        Random random = new Random();
        switch (random.nextInt() % 5) {
            case 0:
                return "https://img.freepik.com/free-photo/cute-woman-bright-hat-purple-blouse-is-leaning-stand-with-dresses-posing-with-package-isolated-background_197531-17610.jpg";
            case 1:
                return "https://s14354.pcdn.co/wp-content/uploads/2019/01/clothes-hanging.jpg";
            case 2:
                return "https://images.herzindagi.info/image/2022/May/clothes-to-repeat-fashion-tips.jpg";
            case 3:
                return "https://cdn.shopify.com/s/files/1/0070/7032/files/how_20to_20start_20a_20clothing_20brand.png?v=1693935729";
            default:
                return "https://d1fufvy4xao6k9.cloudfront.net/images/blog/posts/2023/07/hockerty_cary_grant_8543d7bd_cc53_4d91_b50f_732d6919908a.jpg";
        }
    }
}
