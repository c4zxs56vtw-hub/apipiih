package com.ckennedy.api.config;

import com.ckennedy.api.look.LookEntity;
import com.ckennedy.api.look.LookRepository;
import com.ckennedy.api.product.ProductColorEntity;
import com.ckennedy.api.product.ProductEntity;
import com.ckennedy.api.product.ProductImagesEntity;
import com.ckennedy.api.product.ProductRepository;
import com.ckennedy.api.user.Role;
import com.ckennedy.api.user.UserEntity;
import com.ckennedy.api.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final LookRepository lookRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            UserRepository userRepository,
            ProductRepository productRepository,
            LookRepository lookRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.lookRepository = lookRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedAdminUser();
        seedProducts();
        seedLooks();
    }

    private void seedAdminUser() {
        String adminEmail = System.getenv("CKENNEDY_ADMIN_EMAIL");
        String adminPassword = System.getenv("CKENNEDY_ADMIN_PASSWORD");

        if (adminEmail == null || adminEmail.isBlank()
                || adminPassword == null || adminPassword.isBlank()) {
            return;
        }

        if (!userRepository.existsByEmailIgnoreCase(adminEmail)) {
            UserEntity admin = new UserEntity();
            admin.setName("Propriétaire C'Kennedy");
            admin.setEmail(adminEmail.trim().toLowerCase());
            admin.setPasswordHash(passwordEncoder.encode(adminPassword));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }

    private void seedProducts() {
        if (productRepository.count() > 0) {
            return;
        }

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        // 1. Kinetic Lycra Short
        ProductEntity p1 = new ProductEntity();
        p1.setId("ckennedy-equinox-lycra");
        p1.setName("C'Kennedy Kinetic Lycra Short");
        p1.setSubtitle("Short de sport stretch");
        p1.setCategory("urbain");
        p1.setPrice(69600L);
        p1.setSeason("Collection 2025");
        p1.setBadge("Sport");
        p1.setDescription("Le short C'Kennedy pensé pour bouger : tissu stretch léger qui suit chaque mouvement, sèche vite et reste agréable même sous la chaleur. Idéal pour la salle, le footing du matin ou un look sport décontracté au quotidien.");
        p1.setInStock(true);
        p1.setIsPublished(true);
        p1.setLikesCount(148L);
        p1.setRating(4.9);
        p1.setReviewsCount(148);
        p1.setSizes(List.of("S", "M", "L", "XL"));
        p1.setSpecs(List.of(
                "4-way kinetic compression fabric",
                "Anti-chafe flatlock seams with bonded hem",
                "Internal smartphone bounce-free pocket",
                "Antimicrobial silver-ion yarn treatment"
        ));

        ProductImagesEntity img1 = new ProductImagesEntity();
        img1.setPrimary("https://lh3.googleusercontent.com/aida-public/AB6AXuBmM6LbdJibSnRtWz8WgLoZSRmLeY9znEO9HEO_SI8rLuMdO3x8XKbwKRFBSzQGgC3BUMiEhfo305ZxKv_iHeJ95zkbATlBAEMd3bAnyndiE2UpnYK6yPuIHGIM0XmfEbt3zqw7d3pvih476R4cD6mEkGfaTQqWBjIYiYONTNDmy-6W1umEL4AT-8-2s8L8m4ybWJ_qvEbd7BMjBIzUEMnDs3SEJusQ-idDofqld-Jf7_zXQ735J4kYgg");
        img1.setDetail("https://lh3.googleusercontent.com/aida-public/AB6AXuA3q7qHNwhKvG6MGegJaHIEPiR63TL91AH-bfzNJmUmxWWkqZSXNdT1Tk8aMyYuo4No7gdLT-tgAoduFn4gioNG_WHTHwn4kHX2KFUTJzfWFbK_NyaT2qgi1mguJqj3XP8j2tyD4xI35cwE8uU25IMHTPO8R4Alvpd4xN7ePSb34XqlegSysxUPk4L13wNaqqKd1PnaT1FYBRPrlPFcFz48XSm2t9MQ7nti99n4VaLt16QYsY_iWDnbqQ");
        img1.setCartThumbnail("https://lh3.googleusercontent.com/aida-public/AB6AXuB099eRj5rfbbjAnUN3O5KhYeAp0MPLzlAJGfHeQV6Ay_Qvj-jl8hDhdfSra0WoFiMCKlgUe9bySWakiuzFMwvCE6JUxCl5GCPMM6ouwVlp0nWq-zN3l6M_l8PH6xls17x6caSLo1O4wsXQgS0nr3qJeLg06L5Y4P4nGe0OnztLthRRj7yq2B1JqIrjACsUIZ6J30LbQ-lk7xMbT38rKBKaeVzCXqgmrok2D0ZYI4G-j0RUSH7XMt97Aw");
        p1.setImages(img1);

        ProductColorEntity c1a = new ProductColorEntity();
        c1a.setName("Slate Charcoal");
        c1a.setHex("#26292B");
        ProductColorEntity c1b = new ProductColorEntity();
        c1b.setName("Onyx Black");
        c1b.setHex("#111315");
        p1.setColors(List.of(c1a, c1b));

        // 2. Tactical Sand Heavy Hoodie
        ProductEntity p2 = new ProductEntity();
        p2.setId("ckennedy-tactical-sand-hoodie");
        p2.setName("Tactical Sand Heavy Hoodie");
        p2.setSubtitle("Sweat à capuche épais");
        p2.setCategory("urbain");
        p2.setPrice(88800L);
        p2.setSeason("Collection 2025");
        p2.setBadge("Urbain");
        p2.setDescription("Conçu pour résister aux éléments avec un molleton lourd 480 GSM, coutures renforcées et coupe boxy contemporaine.");
        p2.setInStock(true);
        p2.setIsPublished(true);
        p2.setLikesCount(132L);
        p2.setRating(4.9);
        p2.setReviewsCount(112);
        p2.setSizes(List.of("S", "M", "L", "XL"));
        p2.setSpecs(List.of("480 GSM French Terry", "Double-layered hood", "Kangaroo pocket with internal phone slot"));

        ProductImagesEntity img2 = new ProductImagesEntity();
        img2.setPrimary("https://lh3.googleusercontent.com/aida-public/AB6AXuCuwd7vcgzC2Ap6WDyKelDkq0xTKn80eFahQJbjIqOG_mQt6KI6ex3zxk2NwRoWMZl6wjEx2PmSxWGm-3o8rLwKeQGKqI7ZxfkrGAyemkqzDdF0yoAK3S7OcbjHa0lWBusrLS77fWUMQQLrMByfl3w45sxWPmIj3PrOEeeYqRJEDDgceUilbYnwf47vTCrWCmb100wMhtsq22nMknjTIpHcMm32exUXIwAbI6UFAKS_aXAEtj344h43jA");
        img2.setSecondary("https://lh3.googleusercontent.com/aida-public/AB6AXuAPtSxzs12VCf4H39uQCkntddojHlf7eiOmuVIFEa-RtnFj1ilmELFWTpGlxDQbAfsN1abfqATKdRfsKUqTZXBQlGZxDdIoTzp3cOo7Gvkre0nekuFBFHFEvnHT7BBq3q5AiwGQW7e-h9FwQSBoErpnnpSVz9YjTr7z-Hij--QY-S71BRmxUSi3wezwyD4OFNMLGyyOmFZ8qX3K1jug3rC-Mq1UKic6r7kACQ1cf0FUVWHS9gejsk-9uw");
        img2.setCartThumbnail("https://lh3.googleusercontent.com/aida-public/AB6AXuCuwd7vcgzC2Ap6WDyKelDkq0xTKn80eFahQJbjIqOG_mQt6KI6ex3zxk2NwRoWMZl6wjEx2PmSxWGm-3o8rLwKeQGKqI7ZxfkrGAyemkqzDdF0yoAK3S7OcbjHa0lWBusrLS77fWUMQQLrMByfl3w45sxWPmIj3PrOEeeYqRJEDDgceUilbYnwf47vTCrWCmb100wMhtsq22nMknjTIpHcMm32exUXIwAbI6UFAKS_aXAEtj344h43jA");
        p2.setImages(img2);

        ProductColorEntity c2a = new ProductColorEntity();
        c2a.setName("Desert Sand");
        c2a.setHex("#A39988");
        ProductColorEntity c2b = new ProductColorEntity();
        c2b.setName("Onyx Black");
        c2b.setHex("#111315");
        p2.setColors(List.of(c2a, c2b));

        // 3. Apex Cargo Pant
        ProductEntity p3 = new ProductEntity();
        p3.setId("ckennedy-tactical-cargo");
        p3.setName("Apex Tactical Cargo Pant");
        p3.setSubtitle("Pantalon technique multi-poches");
        p3.setCategory("classique");
        p3.setPrice(105000L);
        p3.setSeason("Collection 2025");
        p3.setBadge("Premium");
        p3.setDescription("Pantalon cargo haut de gamme combinant ripstop technique, coupe ergonomique fuselée et finitions magnétiques.");
        p3.setInStock(true);
        p3.setIsPublished(true);
        p3.setLikesCount(118L);
        p3.setRating(4.8);
        p3.setReviewsCount(94);
        p3.setSizes(List.of("S", "M", "L", "XL"));
        p3.setSpecs(List.of("Cordura Ripstop durable", "Fidlock magnetic pocket closures", "Water-repellent DWR finish"));

        ProductImagesEntity img3 = new ProductImagesEntity();
        img3.setPrimary("https://lh3.googleusercontent.com/aida-public/AB6AXuA3q7qHNwhKvG6MGegJaHIEPiR63TL91AH-bfzNJmUmxWWkqZSXNdT1Tk8aMyYuo4No7gdLT-tgAoduFn4gioNG_WHTHwn4kHX2KFUTJzfWFbK_NyaT2qgi1mguJqj3XP8j2tyD4xI35cwE8uU25IMHTPO8R4Alvpd4xN7ePSb34XqlegSysxUPk4L13wNaqqKd1PnaT1FYBRPrlPFcFz48XSm2t9MQ7nti99n4VaLt16QYsY_iWDnbqQ");
        img3.setCartThumbnail("https://lh3.googleusercontent.com/aida-public/AB6AXuA3q7qHNwhKvG6MGegJaHIEPiR63TL91AH-bfzNJmUmxWWkqZSXNdT1Tk8aMyYuo4No7gdLT-tgAoduFn4gioNG_WHTHwn4kHX2KFUTJzfWFbK_NyaT2qgi1mguJqj3XP8j2tyD4xI35cwE8uU25IMHTPO8R4Alvpd4xN7ePSb34XqlegSysxUPk4L13wNaqqKd1PnaT1FYBRPrlPFcFz48XSm2t9MQ7nti99n4VaLt16QYsY_iWDnbqQ");
        p3.setImages(img3);

        ProductColorEntity c3a = new ProductColorEntity();
        c3a.setName("Stealth Black");
        c3a.setHex("#0D0E10");
        ProductColorEntity c3b = new ProductColorEntity();
        c3b.setName("Tactical Olive");
        c3b.setHex("#4B5320");
        p3.setColors(List.of(c3a, c3b));

        // 4. Cybernetic Track Jacket
        ProductEntity p4 = new ProductEntity();
        p4.setId("ckennedy-cybernetic-jacket");
        p4.setName("Cybernetic Shell Track Jacket");
        p4.setSubtitle("Veste imperméable coupe-vent");
        p4.setCategory("urbain");
        p4.setPrice(130000L);
        p4.setSeason("Collection 2025");
        p4.setBadge("Innovation");
        p4.setDescription("Membrane 3 couches respirante et imperméable conçue pour affronter la météo tropicale avec élégance.");
        p4.setInStock(true);
        p4.setIsPublished(true);
        p4.setLikesCount(165L);
        p4.setRating(5.0);
        p4.setReviewsCount(78);
        p4.setSizes(List.of("M", "L", "XL"));
        p4.setSpecs(List.of("3-Layer breathable membrane 20k/20k", "YKK Aquaguard taped zippers", "Reflective 3M detailing"));

        ProductImagesEntity img4 = new ProductImagesEntity();
        img4.setPrimary("https://lh3.googleusercontent.com/aida-public/AB6AXuB099eRj5rfbbjAnUN3O5KhYeAp0MPLzlAJGfHeQV6Ay_Qvj-jl8hDhdfSra0WoFiMCKlgUe9bySWakiuzFMwvCE6JUxCl5GCPMM6ouwVlp0nWq-zN3l6M_l8PH6xls17x6caSLo1O4wsXQgS0nr3qJeLg06L5Y4P4nGe0OnztLthRRj7yq2B1JqIrjACsUIZ6J30LbQ-lk7xMbT38rKBKaeVzCXqgmrok2D0ZYI4G-j0RUSH7XMt97Aw");
        img4.setCartThumbnail("https://lh3.googleusercontent.com/aida-public/AB6AXuB099eRj5rfbbjAnUN3O5KhYeAp0MPLzlAJGfHeQV6Ay_Qvj-jl8hDhdfSra0WoFiMCKlgUe9bySWakiuzFMwvCE6JUxCl5GCPMM6ouwVlp0nWq-zN3l6M_l8PH6xls17x6caSLo1O4wsXQgS0nr3qJeLg06L5Y4P4nGe0OnztLthRRj7yq2B1JqIrjACsUIZ6J30LbQ-lk7xMbT38rKBKaeVzCXqgmrok2D0ZYI4G-j0RUSH7XMt97Aw");
        p4.setImages(img4);

        ProductColorEntity c4a = new ProductColorEntity();
        c4a.setName("Graphite Grey");
        c4a.setHex("#383838");
        p4.setColors(List.of(c4a));

        productRepository.saveAll(List.of(p1, p2, p3, p4));
    }

    private void seedLooks() {
        if (lookRepository.count() > 0) {
            return;
        }

        LookEntity l1 = new LookEntity();
        l1.setId("look-01");
        l1.setNumber("01");
        l1.setName("C'Kennedy Core / Gamme Classique");
        l1.setSubtitle("Élégance intemporelle & Couture moderne");
        l1.setDate("12/08/2024");
        l1.setDeliveryTag("LIVRAISON MONDIALE");
        l1.setBadge("SUBLIMEZ VOTRE STYLE");
        l1.setHeroImage("https://lh3.googleusercontent.com/aida-public/AB6AXuCrWjdjHB_rqjmPECttix1LWvVqFkYjdWLKSHS3OpGEPxLsvHPb3Wg1ZfvOcSwgqUEEVF1tkSza0-rtq7-tIx184U2okbkWh8wsKa-zbZZhd3cLHAxchNAz5em8BwKNRdigjtnEOaSPDwHoX4eRl83vVILq0BFCV2GqvoLKEmPj4HAw18qmnH06nk89UoJNI098gSiPAatcPJbmCNFbGVUWmNX2hClND9pHAHF8wX432YtIezeYkuRYVg");
        l1.setTopLeftTitle("C'KENNEDY // MODE");
        l1.setTopLeftSubtitle("GAMME CLASSIQUE");
        l1.setTopRightTitle("NOUVELLE SAISON");
        l1.setTopRightSubtitle("COLLECTION");
        l1.setBottomLeftTitle("ÉLÉGANCE");
        l1.setBottomLeftSubtitle("INTEMPORELLE 2025");
        l1.setBottomRightTitle("• VESTE & CHEMISE");
        l1.setBottomRightSubtitle("COUPE AJUSTÉE");
        l1.setIncludedProductIds(List.of("ckennedy-tactical-sand-hoodie", "ckennedy-tactical-cargo"));

        LookEntity l2 = new LookEntity();
        l2.setId("look-02");
        l2.setNumber("02");
        l2.setName("Urbain Chic / Gamme Street");
        l2.setSubtitle("Style contemporain & Vie urbaine");
        l2.setDate("12/08/2024");
        l2.setDeliveryTag("LIVRAISON RAPIDE");
        l2.setBadge("SCELLEZ VOTRE ENGAGEMENT");
        l2.setHeroImage("https://lh3.googleusercontent.com/aida-public/AB6AXuCC_n5PnNViZHqNDnFAGpkOvnudjJE5RNiDjRuTAtYPmMfGoU0jCWcsSI9vOMNO9qEYwZ96jO0Mpaa7NtES23Y49_PpvDyqTZtij6lix4cafnSuvBFjxniHI_jhGQF0LjDM-b6GwicwNSR2jsiSuzYrbhLpuaKVRCh5--oT1AEJ11T8WcFQg6p_xMxN1rjyXc6MgXzpwnyyyse0tU5KIkl4FvG6qFxkVGWfZ_CfWXJpFs6MUEwzF4I8ug");
        l2.setTopLeftTitle("STYLE URBAIN");
        l2.setTopLeftSubtitle("STREET-CHIC");
        l2.setTopRightTitle("12/08/2024");
        l2.setTopRightSubtitle("SAISON");
        l2.setBottomLeftTitle("TENDANCE");
        l2.setBottomLeftSubtitle("AUTOMNE 2025");
        l2.setBottomRightTitle("• VESTE & PANTALON");
        l2.setBottomRightSubtitle("COUPE MODERNE");
        l2.setIncludedProductIds(List.of("ckennedy-tactical-cargo", "ckennedy-tactical-sand-hoodie"));

        LookEntity l3 = new LookEntity();
        l3.setId("look-03");
        l3.setNumber("03");
        l3.setName("Accessoires Signature / Look Complet");
        l3.setSubtitle("Montre, chaîne & maroquinerie de caractère");
        l3.setDate("12/08/2024");
        l3.setDeliveryTag("PRIORITÉ EXPÉDITION");
        l3.setBadge("ACCESSOIRES PREMIUM");
        l3.setHeroImage("https://lh3.googleusercontent.com/aida-public/AB6AXuAK9CWHU-BOp0yn9Zq5pihjzUx4nd-dNjTsddmpfWgrY_MJKf2RM8V_RJRA6ulMiq8xdP9s7LzA2eqak6ob0ZJ-35zOmlDOxeN-T4z_AYx67ldzV1VS-lz2HW-_EoXuRsPD2fRBuOsNHN7OI-7VSg7hA5IfuHUoIwnql3uY04iyMqhv3Dydd4QSMWNKEbG8ZnTr6lgDO9lGtAukEe88T-2uOI9gxwZERP5cVmoXr090SQnQqKUGvMqSvg");
        l3.setTopLeftTitle("ACCESSOIRES");
        l3.setTopLeftSubtitle("COLLECTION SIGNATURE");
        l3.setTopRightTitle("12/08/2024");
        l3.setTopRightSubtitle("SAISON");
        l3.setBottomLeftTitle("FINITIONS");
        l3.setBottomLeftSubtitle("OR & ACIER 2025");
        l3.setBottomRightTitle("• BRACELET & BAGUES");
        l3.setBottomRightSubtitle("PIÈCES LIMITÉES");
        l3.setIncludedProductIds(List.of("ckennedy-cybernetic-jacket"));

        lookRepository.saveAll(List.of(l1, l2, l3));
    }
}
