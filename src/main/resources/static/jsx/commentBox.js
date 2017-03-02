var converter = new Showdown.converter();

var CommentForm = React.createClass({
    handleSubmit: function (e) {
        e.preventDefault();
        var author = this.refs.author.getDOMNode().value.trim();
        var text = this.refs.text.getDOMNode().value.trim();
        if (!author || !text) {
            return;
        }
        this.props.onCommentSubmit({author: author, text: text});
        this.refs.author.getDOMNode().value = '';
        this.refs.text.getDOMNode().value = '';
    },
    render: function () {
        return (
            <form className="row commentForm" onSubmit={this.handleSubmit}>
                <div className="col-md-3"><input className="commentFormInput" type="text" placeholder="Your name" ref="author" /></div>
                <div className="col-md-7"><input className="commentFormInput" type="text" placeholder="Say something..." ref="text" /></div>
                <div className="col-md-2"><input className="commentFormInput" type="submit" value="Post" /></div>
            </form>
        );
    }
});

var Comment = React.createClass({
    render: function () {
        var rawMarkup = converter.makeHtml(this.props.children.toString());
        return (
            <div className="row comment">
                <div className="col-md-3">{this.props.author}</div>
                <div className="col-md-9" dangerouslySetInnerHTML={{__html: rawMarkup}} />
            </div>
        );
    }
});

var CommentList = React.createClass({
    render: function () {
        var commentNodes = this.props.data.map(function (comment, index) {
            return (
                <Comment author={comment.author} key={index}>
                    {comment.text}
                </Comment>
            );
        });
        return (
            <div className="commentList">
                {commentNodes}
            </div>
        );
    }
});

var CommentBox = React.createClass({
    handleCommentSubmit: function (comment) {
        var comments = this.state.data;
        comments.push(comment);
        this.setState({data: comments}, function () {
            $.ajax({
                url: this.props.url,
                dataType: 'json',
                type: 'POST',
                data: comment,
                success: function (data) {
                    this.setState({data: data});
                }.bind(this),
                error: function (xhr, status, err) {
                    console.error(this.props.url, status, err.toString());
                }.bind(this)
            });
        });
    },
    loadCommentsFromServer: function () {
        $.ajax({
            url: this.props.url,
            dataType: 'json',
            success: function (data) {
                this.setState({data: data});
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    },
    getInitialState: function () {
        return {data: this.props.data};
    },
    componentDidMount: function () {
        this.loadCommentsFromServer();
        setInterval(this.loadCommentsFromServer, this.props.pollInterval);
    },
    render: function () {
        return (
            <div className="commentBox">
                <h1>Comments</h1>
                <CommentList data={this.state.data} />
                <CommentForm onCommentSubmit={this.handleCommentSubmit} />
            </div>
        );
    }
});

var renderClient = function (comments) {
    var data = comments || [];
    React.render(
        <CommentBox data={data} url='comments' pollInterval={30000} />,
        document.getElementById("content")
    );
};

var renderServer = function (comments) {
    var data = Java.from(comments);
    return React.renderToString(
        <CommentBox data={data} url='comments' pollInterval={30000} />
    );
};