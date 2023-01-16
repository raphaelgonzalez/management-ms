# Management-MS

## Endpoints

- BaseURL: /managers
- POST: create()
- GET: getAll()
- GET /{id}: getById()
- GET /tasks: getAllTasks()
- PUT /{id}: update()
- DELETE /{id}: delete()
- GET /export: exportData() -- Responsavel por exportar para csv os dados que existem no banco de dados.

## Model
``` json
    {
        "id": 1,
        "name": "Caio Costa",
        "tasks": [{
                "id": 1,
                "description": "Realizar setup do projeto",
                "step": "In Progress"
            },
            {
                "id": 2,
                "description": "Criar serviços",
                "step": "To Do"
            },
            {
                "id": 3,
                "description": "Baixar todas as dependências",
                "step": "Done"
            }]
    }
```