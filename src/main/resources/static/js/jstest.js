var funcManager = (function () {
	var f1 = function(arg) {};
	var f2 = function() {};
	
	return {aa:f1, bb:f2};
})();

funcManager.aa("가변값");