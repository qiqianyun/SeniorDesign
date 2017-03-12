require('dotenv').config();

// Module dependencies
var express = require('express');
// create instance
var app = express();

var bodyParser = require('body-parser');
var mongoose = require('mongoose');

app.use(bodyParser.json());

Category = require('./models/category');
Item = require('./models/item');
Review = require('./models/review');

//Connect to Mongoose
var connection_options = {
	user: process.env.MONGO_DB_USER,
	pass: process.env.MONGO_DB_PASS
};
mongoose.connect(process.env.MONGO_DB_URL,
					connection_options);
//mongoose.connect('mongodb://localhost/rent_it');
//mongoose.connect('mongodb://localhost/bookstore');
var db = mongoose.connection;

/*app.get('/', function(req,res){
	res.send('Hello World! Please user /api/books or /api/genres');
});*/

//get all
app.get('/api/categories',function(req,res){
	console.log("reached the server");
	Category.getCategories(function(err,categories){
		if(err){
			throw err;
		}
		res.json(categories);
	});
});


/*app.post('/api/categories',function(req,res){
	var genre = req.body;
	Genre.addGenre(genre,function(err,genre){
		if(err){
			throw err;
		}
		res.json(genre);
	});
});*/

/*app.put('/api/categories/:_id',function(req,res){
	var id = req.params._id;
	var category = req.body;
	Category.updateCategory(id, category, {}, function(err,category){
		if(err){
			throw err;
		}
		res.json(category);
	});
});*/

/*app.delete('/api/genres/:_id',function(req,res){
	var id = req.params._id;
	var genre = req.body;
	Genre.removeGenre(id, function(err,genre){
		if(err){
			throw err;
		}
		res.json(genre);
	});
});*/

/*Item.find({}, function(err,items){
	if (err) throw err;

	console.log(items);
});*/
//get all items
app.get('/api/items',function(req,res){
	console.log("reached the server");
	Item.getItems(function(err, items){
		if(err){
			throw err;
		}
		res.json(items);
	});
});
//get item by id
app.get('/api/items/:_id',function(req,res){
	Item.getItemById(req.params._id,function(err,items){
		if(err){
			throw err;
		}
		res.json(items);
	})
});
//get item by category
app.get('/api/items/category/:category',function(req,res){
	Item.getItemsByCategoryId(req.params.category,function(err,items){
		if(err){
			throw err;
		}
		res.json(items);
	})
});
//get one item by uid
app.get('/api/items/user/:uid',function(req,res){
	Item.getItemsByUid(req.params.uid,function(err,items){
		if(err){
			throw err;
		}
		res.json(items);
	})
});
//add new item
app.post('/api/items',function(req,res){
	var item = req.body;
	Item.addItem(item,function(err,item){
		if(err){
			throw err;
		}
		res.json(item);
	});
});
/*
app.put('/api/books/:_id',function(req,res){
	var id = req.params._id;
	var book = req.body;
	Book.updateBook(id, book, {}, function(err,book){
		if(err){
			throw err;
		}
		res.json(book);
	});
});
*/
//Delete Item
/*app.delete('/api/items/:_id',function(req,res){
	var id = req.params._id;
	var item = req.body;
	Item.removeItem(id, function(err,item){
		if(err){
			throw err;
		}
		res.json(item);
	});
});*/

//get all reviews
app.get('/api/reviews',function(req,res){
	console.log("reached the server");
	Review.getReviews(function(err, reviews){
		if(err){
			throw err;
		}
		res.json(reviews);
	});
});

//get latest review by item id
app.get('/api/review/item/:item',function(req,res){
	Review.getLatestReviewByItemId(req.params.item,function(err,review){
		if(err){
			throw err;
		}
		if(!review){
			//(review == null) works too
			console.log("review is null 1");
			review =[];
			console.log("review is now " + review);
		}
	
		res.json(review);
	})
});

//get review by item id
app.get('/api/reviews/item/:item',function(req,res){
	Review.getReviewsByItemId(req.params.item,function(err,reviews){
		if(err){
			throw err;
		}

			console.log("review is  " + reviews);
		res.json(reviews);
	})
});

app.listen(process.env.PORT_NO);
console.log('Running on port 3000...');

// Router
/*var router = require('./routes/router.js')
app.use('/', router);*/

// Setup Server configuration
/*var port = process.env.PORT || 3000;
app.listen(port);
console.log('Node is running on port ' + port);*/

