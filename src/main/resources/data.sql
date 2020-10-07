delete from springApp;

insert into springApp (key, value) values
        (uuid(), { '_type': 'ingredient', 'id': 'FLTO', 'name': 'Flour Tortilla', 'type': 'WRAP' })
        , (uuid(), { '_type': 'ingredient', 'id': 'COTO', 'name': 'Corn Tortilla', 'type': 'WRAP' })
        , (uuid(), { '_type': 'ingredient', 'id': 'GRBF', 'name': 'Ground Beef', 'type': 'PROTEIN' })
        , (uuid(), { '_type': 'ingredient', 'id': 'CARN', 'name': 'Carnitas', 'type': 'PROTEIN' })
        , (uuid(), { '_type': 'ingredient', 'id': 'TMTO', 'name': 'Diced Tomatoes', 'type': 'VEGGIES' })
        , (uuid(), { '_type': 'ingredient', 'id': 'LETC', 'name': 'Lettuce', 'type': 'VEGGIES' })
        , (uuid(), { '_type': 'ingredient', 'id': 'CHED', 'name': 'Cheddar', 'type': 'CHEESE' })
        , (uuid(), { '_type': 'ingredient', 'id': 'JACK', 'name': 'Monterrey Jack', 'type': 'CHEESE' })
        , (uuid(), { '_type': 'ingredient', 'id': 'SLSA', 'name': 'Salsa', 'type': 'SAUCE' })
        , (uuid(), { '_type': 'ingredient', 'id': 'SRCR', 'name': 'Sour Cream', 'type': 'SAUCE' });
