module.exports = function(grunt) {
    require("load-grunt-tasks")(grunt);

    grunt.initConfig({
      "babel": {
        options: {
          sourceMap: true
        },
        dist: {
          files: {
            "src/main/resources/static/js/gen/commentBox.js": "src/main/resources/static/jsx/commentBox.js"
          }
        }
      }
    });

    grunt.registerTask("default", ["babel"]);
};
