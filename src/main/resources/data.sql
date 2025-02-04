DROP TABLE IF EXISTS PRODUCT;

CREATE TABLE PRODUCT (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    price DECIMAL(10,2),
    IMAGE_URL TEXT
);




INSERT INTO PRODUCT (name, description, price, IMAGE_URL) VALUES 
('Laptop', 'High-performance laptop', 999.99, 'https://i.pcmag.com/imagery/roundups/02naaOkVLe7DIiejFUyDPJp-57..v1714766094.jpg'),
('Smartphone', 'Latest model smartphone', 599.99, 'https://cdn.thewirecutter.com/wp-content/media/2024/09/iphone-2048px-6959.jpg?auto=webp&quality=75&width=1024'),
('Headphones', 'Noise-cancelling wireless headphones', 249.99, 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/MQTR3?wid=1144&hei=1144&fmt=jpeg&qlt=90&.v=1687660671097');


