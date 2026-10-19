-- Seed Initial Products for C'Kennedy Store

DELETE FROM product_colors;
DELETE FROM product_sizes;
DELETE FROM product_specs;
DELETE FROM products;
DELETE FROM look_product_ids;
DELETE FROM looks;

-- 1. Product: Kinetic Lycra Short
INSERT INTO products (
    id, name, subtitle, category, price, season, badge, description,
    image_primary, image_detail, cart_thumbnail, in_stock, is_published,
    likes_count, rating, reviews_count, created_at, updated_at
) VALUES (
    'ckennedy-equinox-lycra',
    'C''Kennedy Kinetic Lycra Short',
    'Short de sport stretch',
    'urbain',
    69600,
    'Collection 2025',
    'Sport',
    'Le short C''Kennedy pensé pour bouger : tissu stretch léger qui suit chaque mouvement, sèche vite et reste agréable même sous la chaleur. Idéal pour la salle, le footing du matin ou un look sport décontracté au quotidien.',
    'https://lh3.googleusercontent.com/aida-public/AB6AXuBmM6LbdJibSnRtWz8WgLoZSRmLeY9znEO9HEO_SI8rLuMdO3x8XKbwKRFBSzQGgC3BUMiEhfo305ZxKv_iHeJ95zkbATlBAEMd3bAnyndiE2UpnYK6yPuIHGIM0XmfEbt3zqw7d3pvih476R4cD6mEkGfaTQqWBjIYiYONTNDmy-6W1umEL4AT-8-2s8L8m4ybWJ_qvEbd7BMjBIzUEMnDs3SEJusQ-idDofqld-Jf7_zXQ735J4kYgg',
    'https://lh3.googleusercontent.com/aida-public/AB6AXuA3q7qHNwhKvG6MGegJaHIEPiR63TL91AH-bfzNJmUmxWWkqZSXNdT1Tk8aMyYuo4No7gdLT-tgAoduFn4gioNG_WHTHwn4kHX2KFUTJzfWFbK_NyaT2qgi1mguJqj3XP8j2tyD4xI35cwE8uU25IMHTPO8R4Alvpd4xN7ePSb34XqlegSysxUPk4L13wNaqqKd1PnaT1FYBRPrlPFcFz48XSm2t9MQ7nti99n4VaLt16QYsY_iWDnbqQ',
    'https://lh3.googleusercontent.com/aida-public/AB6AXuB099eRj5rfbbjAnUN3O5KhYeAp0MPLzlAJGfHeQV6Ay_Qvj-jl8hDhdfSra0WoFiMCKlgUe9bySWakiuzFMwvCE6JUxCl5GCPMM6ouwVlp0nWq-zN3l6M_l8PH6xls17x6caSLo1O4wsXQgS0nr3qJeLg06L5Y4P4nGe0OnztLthRRj7yq2B1JqIrjACsUIZ6J30LbQ-lk7xMbT38rKBKaeVzCXqgmrok2D0ZYI4G-j0RUSH7XMt97Aw',
    true, true, 148, 4.9, 148, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);

INSERT INTO product_sizes (product_id, size, size_order) VALUES
('ckennedy-equinox-lycra', 'S', 0),
('ckennedy-equinox-lycra', 'M', 1),
('ckennedy-equinox-lycra', 'L', 2),
('ckennedy-equinox-lycra', 'XL', 3);

INSERT INTO product_colors (product_id, name, hex, color_order) VALUES
('ckennedy-equinox-lycra', 'Slate Charcoal', '#26292B', 0),
('ckennedy-equinox-lycra', 'Onyx Black', '#111315', 1);

INSERT INTO product_specs (product_id, spec, spec_order) VALUES
('ckennedy-equinox-lycra', '4-way kinetic compression fabric', 0),
('ckennedy-equinox-lycra', 'Anti-chafe flatlock seams with bonded hem', 1),
('ckennedy-equinox-lycra', 'Internal smartphone bounce-free pocket', 2),
('ckennedy-equinox-lycra', 'Antimicrobial silver-ion yarn treatment', 3);

-- 2. Product: Tactical Sand Heavy Hoodie
INSERT INTO products (
    id, name, subtitle, category, price, season, badge, description,
    image_primary, image_secondary, cart_thumbnail, in_stock, is_published,
    likes_count, rating, reviews_count, created_at, updated_at
) VALUES (
    'ckennedy-tactical-sand-hoodie',
    'Tactical Sand Heavy Hoodie',
    'Sweat à capuche épais',
    'urbain',
    88800,
    'Collection 2025',
    'Urbain',
    'Conçu pour résister aux éléments avec un molleton lourd 480 GSM, coutures renforcées et coupe boxy contemporaine.',
    'https://lh3.googleusercontent.com/aida-public/AB6AXuCuwd7vcgzC2Ap6WDyKelDkq0xTKn80eFahQJbjIqOG_mQt6KI6ex3zxk2NwRoWMZl6wjEx2PmSxWGm-3o8rLwKeQGKqI7ZxfkrGAyemkqzDdF0yoAK3S7OcbjHa0lWBusrLS77fWUMQQLrMByfl3w45sxWPmIj3PrOEeeYqRJEDDgceUilbYnwf47vTCrWCmb100wMhtsq22nMknjTIpHcMm32exUXIwAbI6UFAKS_aXAEtj344h43jA',
    'https://lh3.googleusercontent.com/aida-public/AB6AXuAPtSxzs12VCf4H39uQCkntddojHlf7eiOmuVIFEa-RtnFj1ilmELFWTpGlxDQbAfsN1abfqATKdRfsKUqTZXBQlGZxDdIoTzp3cOo7Gvkre0nekuFBFHFEvnHT7BBq3q5AiwGQW7e-h9FwQSBoErpnnpSVz9YjTr7z-Hij--QY-S71BRmxUSi3wezwyD4OFNMLGyyOmFZ8qX3K1jug3rC-Mq1UKic6r7kACQ1cf0FUVWHS9gejsk-9uw',
    'https://lh3.googleusercontent.com/aida-public/AB6AXuCuwd7vcgzC2Ap6WDyKelDkq0xTKn80eFahQJbjIqOG_mQt6KI6ex3zxk2NwRoWMZl6wjEx2PmSxWGm-3o8rLwKeQGKqI7ZxfkrGAyemkqzDdF0yoAK3S7OcbjHa0lWBusrLS77fWUMQQLrMByfl3w45sxWPmIj3PrOEeeYqRJEDDgceUilbYnwf47vTCrWCmb100wMhtsq22nMknjTIpHcMm32exUXIwAbI6UFAKS_aXAEtj344h43jA',
    true, true, 132, 4.9, 112, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);

INSERT INTO product_sizes (product_id, size, size_order) VALUES
('ckennedy-tactical-sand-hoodie', 'S', 0),
('ckennedy-tactical-sand-hoodie', 'M', 1),
('ckennedy-tactical-sand-hoodie', 'L', 2),
('ckennedy-tactical-sand-hoodie', 'XL', 3);

INSERT INTO product_colors (product_id, name, hex, color_order) VALUES
('ckennedy-tactical-sand-hoodie', 'Desert Sand', '#A39988', 0),
('ckennedy-tactical-sand-hoodie', 'Onyx Black', '#111315', 1);

INSERT INTO product_specs (product_id, spec, spec_order) VALUES
('ckennedy-tactical-sand-hoodie', '480 GSM French Terry', 0),
('ckennedy-tactical-sand-hoodie', 'Double-layered hood', 1),
('ckennedy-tactical-sand-hoodie', 'Kangaroo pocket with internal phone slot', 2);

-- 3. Product: Apex Tactical Cargo Pant
INSERT INTO products (
    id, name, subtitle, category, price, season, badge, description,
    image_primary, cart_thumbnail, in_stock, is_published,
    likes_count, rating, reviews_count, created_at, updated_at
) VALUES (
    'ckennedy-tactical-cargo',
    'Apex Tactical Cargo Pant',
    'Pantalon technique multi-poches',
    'classique',
    105000,
    'Collection 2025',
    'Premium',
    'Pantalon cargo haut de gamme combinant ripstop technique, coupe ergonomique fuselée et finitions magnétiques.',
    'https://lh3.googleusercontent.com/aida-public/AB6AXuA3q7qHNwhKvG6MGegJaHIEPiR63TL91AH-bfzNJmUmxWWkqZSXNdT1Tk8aMyYuo4No7gdLT-tgAoduFn4gioNG_WHTHwn4kHX2KFUTJzfWFbK_NyaT2qgi1mguJqj3XP8j2tyD4xI35cwE8uU25IMHTPO8R4Alvpd4xN7ePSb34XqlegSysxUPk4L13wNaqqKd1PnaT1FYBRPrlPFcFz48XSm2t9MQ7nti99n4VaLt16QYsY_iWDnbqQ',
    'https://lh3.googleusercontent.com/aida-public/AB6AXuA3q7qHNwhKvG6MGegJaHIEPiR63TL91AH-bfzNJmUmxWWkqZSXNdT1Tk8aMyYuo4No7gdLT-tgAoduFn4gioNG_WHTHwn4kHX2KFUTJzfWFbK_NyaT2qgi1mguJqj3XP8j2tyD4xI35cwE8uU25IMHTPO8R4Alvpd4xN7ePSb34XqlegSysxUPk4L13wNaqqKd1PnaT1FYBRPrlPFcFz48XSm2t9MQ7nti99n4VaLt16QYsY_iWDnbqQ',
    true, true, 118, 4.8, 94, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);

INSERT INTO product_sizes (product_id, size, size_order) VALUES
('ckennedy-tactical-cargo', 'S', 0),
('ckennedy-tactical-cargo', 'M', 1),
('ckennedy-tactical-cargo', 'L', 2),
('ckennedy-tactical-cargo', 'XL', 3);

INSERT INTO product_colors (product_id, name, hex, color_order) VALUES
('ckennedy-tactical-cargo', 'Stealth Black', '#0D0E10', 0),
('ckennedy-tactical-cargo', 'Tactical Olive', '#4B5320', 1);

INSERT INTO product_specs (product_id, spec, spec_order) VALUES
('ckennedy-tactical-cargo', 'Cordura Ripstop durable', 0),
('ckennedy-tactical-cargo', 'Fidlock magnetic pocket closures', 1),
('ckennedy-tactical-cargo', 'Water-repellent DWR finish', 2);

-- 4. Product: Cybernetic Shell Track Jacket
INSERT INTO products (
    id, name, subtitle, category, price, season, badge, description,
    image_primary, cart_thumbnail, in_stock, is_published,
    likes_count, rating, reviews_count, created_at, updated_at
) VALUES (
    'ckennedy-cybernetic-jacket',
    'Cybernetic Shell Track Jacket',
    'Veste imperméable coupe-vent',
    'urbain',
    130000,
    'Collection 2025',
    'Innovation',
    'Membrane 3 couches respirante et imperméable conçue pour affronter la météo tropicale avec élégance.',
    'https://lh3.googleusercontent.com/aida-public/AB6AXuB099eRj5rfbbjAnUN3O5KhYeAp0MPLzlAJGfHeQV6Ay_Qvj-jl8hDhdfSra0WoFiMCKlgUe9bySWakiuzFMwvCE6JUxCl5GCPMM6ouwVlp0nWq-zN3l6M_l8PH6xls17x6caSLo1O4wsXQgS0nr3qJeLg06L5Y4P4nGe0OnztLthRRj7yq2B1JqIrjACsUIZ6J30LbQ-lk7xMbT38rKBKaeVzCXqgmrok2D0ZYI4G-j0RUSH7XMt97Aw',
    'https://lh3.googleusercontent.com/aida-public/AB6AXuB099eRj5rfbbjAnUN3O5KhYeAp0MPLzlAJGfHeQV6Ay_Qvj-jl8hDhdfSra0WoFiMCKlgUe9bySWakiuzFMwvCE6JUxCl5GCPMM6ouwVlp0nWq-zN3l6M_l8PH6xls17x6caSLo1O4wsXQgS0nr3qJeLg06L5Y4P4nGe0OnztLthRRj7yq2B1JqIrjACsUIZ6J30LbQ-lk7xMbT38rKBKaeVzCXqgmrok2D0ZYI4G-j0RUSH7XMt97Aw',
    true, true, 165, 5.0, 78, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);

INSERT INTO product_sizes (product_id, size, size_order) VALUES
('ckennedy-cybernetic-jacket', 'M', 0),
('ckennedy-cybernetic-jacket', 'L', 1),
('ckennedy-cybernetic-jacket', 'XL', 2);

INSERT INTO product_colors (product_id, name, hex, color_order) VALUES
('ckennedy-cybernetic-jacket', 'Graphite Grey', '#383838', 0);

INSERT INTO product_specs (product_id, spec, spec_order) VALUES
('ckennedy-cybernetic-jacket', '3-Layer breathable membrane 20k/20k', 0),
('ckennedy-cybernetic-jacket', 'YKK Aquaguard taped zippers', 1),
('ckennedy-cybernetic-jacket', 'Reflective 3M detailing', 2);

-- Seed Looks
INSERT INTO looks (
    id, number, name, subtitle, display_date, delivery_tag, badge, hero_image,
    top_left_title, top_left_subtitle, top_right_title, top_right_subtitle,
    bottom_left_title, bottom_left_subtitle, bottom_right_title, bottom_right_subtitle
) VALUES (
    'look-01',
    '01',
    'C''Kennedy Core / Gamme Classique',
    'Élégance intemporelle & Couture moderne',
    '12/08/2024',
    'LIVRAISON MONDIALE',
    'SUBLIMEZ VOTRE STYLE',
    'https://lh3.googleusercontent.com/aida-public/AB6AXuCrWjdjHB_rqjmPECttix1LWvVqFkYjdWLKSHS3OpGEPxLsvHPb3Wg1ZfvOcSwgqUEEVF1tkSza0-rtq7-tIx184U2okbkWh8wsKa-zbZZhd3cLHAxchNAz5em8BwKNRdigjtnEOaSPDwHoX4eRl83vVILq0BFCV2GqvoLKEmPj4HAw18qmnH06nk89UoJNI098gSiPAatcPJbmCNFbGVUWmNX2hClND9pHAHF8wX432YtIezeYkuRYVg',
    'C''KENNEDY // MODE',
    'GAMME CLASSIQUE',
    'NOUVELLE SAISON',
    'COLLECTION',
    'ÉLÉGANCE',
    'INTEMPORELLE 2025',
    '• VESTE & CHEMISE',
    'COUPE AJUSTÉE'
), (
    'look-02',
    '02',
    'Urbain Chic / Gamme Street',
    'Style contemporain & Vie urbaine',
    '12/08/2024',
    'LIVRAISON RAPIDE',
    'SCELLEZ VOTRE ENGAGEMENT',
    'https://lh3.googleusercontent.com/aida-public/AB6AXuCC_n5PnNViZHqNDnFAGpkOvnudjJE5RNiDjRuTAtYPmMfGoU0jCWcsSI9vOMNO9qEYwZ96jO0Mpaa7NtES23Y49_PpvDyqTZtij6lix4cafnSuvBFjxniHI_jhGQF0LjDM-b6GwicwNSR2jsiSuzYrbhLpuaKVRCh5--oT1AEJ11T8WcFQg6p_xMxN1rjyXc6MgXzpwnyyyse0tU5KIkl4FvG6qFxkVGWfZ_CfWXJpFs6MUEwzF4I8ug',
    'STYLE URBAIN',
    'STREET-CHIC',
    '12/08/2024',
    'SAISON',
    'TENDANCE',
    'AUTOMNE 2025',
    '• VESTE & PANTALON',
    'COUPE MODERNE'
), (
    'look-03',
    '03',
    'Accessoires Signature / Look Complet',
    'Montre, chaîne & maroquinerie de caractère',
    '12/08/2024',
    'PRIORITÉ EXPÉDITION',
    'ACCESSOIRES PREMIUM',
    'https://lh3.googleusercontent.com/aida-public/AB6AXuAK9CWHU-BOp0yn9Zq5pihjzUx4nd-dNjTsddmpfWgrY_MJKf2RM8V_RJRA6ulMiq8xdP9s7LzA2eqak6ob0ZJ-35zOmlDOxeN-T4z_AYx67ldzV1VS-lz2HW-_EoXuRsPD2fRBuOsNHN7OI-7VSg7hA5IfuHUoIwnql3uY04iyMqhv3Dydd4QSMWNKEbG8ZnTr6lgDO9lGtAukEe88T-2uOI9gxwZERP5cVmoXr090SQnQqKUGvMqSvg',
    'ACCESSOIRES',
    'COLLECTION SIGNATURE',
    '12/08/2024',
    'SAISON',
    'FINITIONS',
    'OR & ACIER 2025',
    '• BRACELET & BAGUES',
    'PIÈCES LIMITÉES'
);

INSERT INTO look_product_ids (look_id, product_id, position) VALUES
('look-01', 'ckennedy-tactical-sand-hoodie', 0),
('look-01', 'ckennedy-tactical-cargo', 1),
('look-02', 'ckennedy-tactical-cargo', 0),
('look-02', 'ckennedy-tactical-sand-hoodie', 1),
('look-03', 'ckennedy-cybernetic-jacket', 0);
