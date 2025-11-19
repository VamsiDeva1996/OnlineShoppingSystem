
# Online Shopping System (Updated with features)

What's included:
- Admin login & dashboard (add/edit/delete products)
- Product images (store path)
- Full cart system (add/update/remove/clear)
- Checkout & order placement (order + order_items, stock update)
- Search & filter products (by name and price range)
- User profile (update email/password/address)
- Order history for users
- Payment simulation (Card / UPI / COD)
- Simple invoice file generated as text in /mnt/data (invoice_<orderId>.txt)

Notes:
- You must edit db/DBConnection.java to set your MySQL username/password.
- Run the SQL in `database.sql` to create schema and sample data.
- Required: MySQL Connector/J jar on classpath when compiling/running.
- PDF invoice generation was replaced by a simple text invoice to avoid external libraries; can be upgraded to PDF by adding iText or similar.
- Spring Boot conversion is not done here; it's a large migration. I can generate a Spring Boot project separately if you want.

To build (example):
- javac -d out -cp "lib/*" src/**/*.java
- java -cp "out:lib/*" Main
