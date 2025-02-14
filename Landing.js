import React, { Component } from 'react'
import {Link} from 'react-router-dom'
import {connect} from "react-redux";
import PropTypes from "prop-types"
//import Register from '../UserManagement/Register'
 class Landing extends Component {
    componentDidMount(){
        if(this.props.security.validToken){
            this.props.history.push("/dashboard");
        }
    }
    render() {
        return (
            <div className="landing">
            <div className="light-overlay landing-inner text-dark">
            <div className="container">
                <div className="row">
                    <div className="col-md-12 text-center">
                        <h5 className="display-4 mb-5">Personal Project Management Tool</h5>
                        <p className="lead">
                            Create your  account to join active projects or start your own
                        </p>
                        <hr />
                        <Link className="btn btn-lg btn-primary mr-2" to="/register">
                            Sign Up
                        </Link>
                        <Link className="btn btn-lg btn-primary mr-2" to="/login">
                            Login
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    </div>


    // <!-- End of Landing Component -->
        )
    }
}
Landing.propTypes={
    security:PropTypes.object.isRequired
};
const mapStateToProps=state=>({
    security:state.security
})
export default  connect(mapStateToProps)(Landing);
