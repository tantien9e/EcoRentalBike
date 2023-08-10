# Eco Bike Rental

## Run 

```sh 
docker compose up -d
```

vào dashboard của docker chạy lại phần sql \
vào đường dẫn localhost:9999\initial để setup data

Nội dung folder
- controllers : chứa các routing
- domain :
  - enums : chứa domain
  - models : chứa các đội tương chính project
- repositories : các phương thức CRUD của mysql
- exceptions : nơi lưu trữ các ngoại lệ
- services : chứa business logic
