.PHONY: start stop restart clean clean-compose logs pgadmin status

start:
	docker compose up -d

stop:
	docker compose down

restart: stop start

clean:
	mvn clean package

clean-compose:
	docker compose down -v --remove-orphans
	docker system prune -f

logs:
	docker compose logs -f

pgadmin:
	open http://localhost:8081

status:
	docker ps
	docker stats --no-stream
