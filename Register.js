import React, { Component } from 'react';
import {createNewUser} from "../../actions/securityActions";
import PropTypes from "prop-types";
import {connect} from "react-redux"
import classnames from "classnames"

 class Register extends Component {
     constructor(){
         super();
         this.state={
            userName:"",
            fullName:"",
            password:"",
            confirmPassword:"",
            errors:{}
            
         };
         this.onChange=this.onChange.bind(this);
         this.onSubmit=this.onSubmit.bind(this);
     }
     componentDidMount(){
        if(this.props.security.validToken){
            this.props.history.push("/dashboard");
        }
    }
     componentWillReceiveProps(nextProps){
         if(nextProps.errors){
             this.setState({errors:nextProps.errors});
         }
     }
     onSubmit(e) {
        e.preventDefault();
        const newUser={
            userName:this.state.userName,
            fullName:this.state.fullName,
            password:this.state.password,
            confirmPassword:this.state.confirmPassword

        };
        this.props.createNewUser(newUser,this.props.history);
    }
     onChange(e){
         this.setState({[e.target.name]:e.target.value})
     }
    render() {
        const {errors}=this.state;
        return (
              
            //<!-- Start of Registration Form -->
            <div className="register">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h1 className="display-4 text-center">Sign Up</h1>
                            <p className="lead text-center">Create your Account</p>
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input type="text" className={classnames("form-control form-control-lg ",{
                                    "is-invalid":errors.fullName
                                    })} placeholder="Name" name="fullName"
                                       value={this.state.fullName} onChange={this.onChange}  />
                                    
                                            {errors.fullName && (
                                                <div className="invalid-feedback">{errors.fullName}</div> 
                                            )}
                                       
                                </div>
                                <div className="form-group">
                                    <input type="text" className={classnames("form-control form-control-lg ",{
                                    "is-invalid":errors.userName
                                    })} 
                                    placeholder="Email Address(userName)"
                                     name="userName" value={this.state.userName} onChange={this.onChange} />
                                     {errors.userName && (
                                                <div className="invalid-feedback">{errors.userName}</div> 
                                            )}
                                       

                                </div>
                                <div className="form-group">
                                    <input type="password" className={classnames("form-control form-control-lg ",{
                                    "is-invalid":errors.password
                                    })} 
                                    // className={classnames("form-control form-control-lg ",{
                                    //     "is-invalid":errors.Password
                                    //     })}
                                    placeholder="Password" 
                                    name="password"  value={this.state.password} onChange={this.onChange}/>
                                    {errors.password && (
                                                <div className="invalid-feedback">{errors.password}</div> 
                                            )}
                                    {/* {errors.Password && (
                                                <div className="invalid-feedback">{errors.Password}</div> 
                                            )} */}
                                </div>
                                <div className="form-group">
                                    <input type="password" className={classnames("form-control form-control-lg ",{
                                    "is-invalid":errors.Password
                                    })} 
                                    placeholder="Confirm Password"
                                        name="confirmPassword" value={this.state.confirmPassword} onChange={this.onChange}  />
                                        {errors.Password && (
                                                <div className="invalid-feedback">{errors.Password}</div> 
                                            )}
                                </div>
                                <input type="submit" className="btn btn-info btn-block mt-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>



   // <!-- End of Registration Form -->
        )
    }
}

Register.propTypes={
    createNewUser:PropTypes.func.isRequired,
    errors:PropTypes.object.isRequired,
    security:PropTypes.object.isRequired

}
const mapStateToProps=state=>({
    errors:state.errors,
    security:state.security
});
export default connect 
(mapStateToProps,{createNewUser})(Register);